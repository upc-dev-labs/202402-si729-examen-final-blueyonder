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

/**
 * Application service implementation for handling OrderItem creation commands.
 * <p>
 * Validates business rules such as SKU existence, uniqueness within an order,
 * and dispatch readiness. Coordinates with external SCM services and persists
 * the resulting OrderItem aggregate.
 * </p>
 *
 * @author Author
 */
@Service
public class OrderItemCommandServiceImpl implements OrderItemCommandService {

    private final OrderItemRepository orderItemRepository;
    private final ExternalScmService externalScmService;

    /**
     * Constructs the OrderItemCommandServiceImpl with required dependencies.
     *
     * @param orderItemRepository Repository for persisting OrderItems.
     * @param externalScmService  ACL service for checking inventory and dispatch readiness.
     */
    public OrderItemCommandServiceImpl(OrderItemRepository orderItemRepository, ExternalScmService externalScmService) {
        this.orderItemRepository = orderItemRepository;
        this.externalScmService = externalScmService;
    }

    /**
     * Handles the creation of an OrderItem based on the provided command.
     * <p>
     * Validates SKU existence, ensures uniqueness within the order,
     * checks dispatch readiness, and determines the resulting status.
     * Persists the OrderItem aggregate.
     * </p>
     *
     * @param command The command containing order details such as orderId, SKU identifier,
     *                requested quantity, and orderedAt date.
     * @return An Optional containing the created OrderItem if successful.
     * @throws InventoryItemNotExistsException                  if the SKU does not exist in inventory.
     * @throws OrderItemWithOrderIdAndSkuIdentifierExistsException if an OrderItem with the same orderId and SKU already exists.
     * @throws IllegalArgumentException                         if an error occurs during persistence.
     */
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
