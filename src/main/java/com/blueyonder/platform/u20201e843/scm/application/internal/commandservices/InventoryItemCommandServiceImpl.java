package com.blueyonder.platform.u20201e843.scm.application.internal.commandservices;

import com.blueyonder.platform.u20201e843.scm.domain.exceptions.InventoryItemAlreadyExistsException;
import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u20201e843.scm.domain.services.InventoryItemCommandService;
import com.blueyonder.platform.u20201e843.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryItemCommandServiceImpl implements InventoryItemCommandService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemCommandServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public Optional<InventoryItem> handle(CreateInventoryItemCommand command) {
        if (inventoryItemRepository.existsBySkuIdentifier(new SkuIdentifier(command.skuIdentifier())))
            throw new InventoryItemAlreadyExistsException(command.skuIdentifier());

        var inventoryItem = new InventoryItem(command.skuIdentifier(), command.minimumQuantity(), command.availableQuantity());

        try {
            inventoryItemRepository.save(inventoryItem);
            return Optional.of(inventoryItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving inventory item: %s".formatted(e.getMessage()));
        }
    }
}
