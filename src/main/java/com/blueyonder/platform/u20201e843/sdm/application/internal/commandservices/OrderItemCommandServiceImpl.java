package com.blueyonder.platform.u20201e843.sdm.application.internal.commandservices;

import com.blueyonder.platform.u20201e843.sdm.application.internal.outboundservices.acl.ExternalScmService;
import com.blueyonder.platform.u20201e843.sdm.domain.exceptions.InventoryItemNotExistsException;
import com.blueyonder.platform.u20201e843.sdm.domain.exceptions.OrderItemWithOrderIdAndSkuIdentifierExistsException;
import com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u20201e843.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u20201e843.sdm.domain.services.OrderItemCommandService;
import com.blueyonder.platform.u20201e843.sdm.infrastructure.persistence.jpa.repositories.OrderItemRepository;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemCommandServiceImpl implements OrderItemCommandService {

    private final OrderItemRepository orderItemRepository;
    private final ExternalScmService externalScmService;

    public OrderItemCommandServiceImpl(OrderItemRepository orderItemRepository, ExternalScmService externalScmService) {
        this.orderItemRepository = orderItemRepository;
        this.externalScmService = externalScmService;
    }

    @Override
    public Optional<OrderItem> handle(CreateOrderItemCommand command) {
        if (!externalScmService.existsInventoryItemBySkuIdentifier(command.skuIdentifier()))
            throw new InventoryItemNotExistsException(command.skuIdentifier());

        if (orderItemRepository.existsByOrderIdAndSkuIdentifier(command.orderId(), new SkuIdentifier(command.skuIdentifier())))
            throw new OrderItemWithOrderIdAndSkuIdentifierExistsException(command.orderId(), command.skuIdentifier());

        var isReadyForDispatch = externalScmService.isReadyForDispatch(command.skuIdentifier(), command.requestedQuantity());
        var orderItem = new OrderItem(command);

        if (isReadyForDispatch) {
            orderItem.readyForDispatch();
        } else {
            orderItem.waitingForInventory();
        }

        try {
            orderItemRepository.save(orderItem);
            return Optional.of(orderItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving order item: %s".formatted(e.getMessage()));
        }
    }
}
