package com.blueyonder.platform.u20201e843.scm.application.internal.commandservices;

import com.blueyonder.platform.u20201e843.scm.domain.exceptions.InventoryItemAlreadyExistsException;
import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u20201e843.scm.domain.services.InventoryItemCommandService;
import com.blueyonder.platform.u20201e843.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Application service implementation for handling InventoryItem creation commands.
 * <p>
 * Validates SKU uniqueness, creates new InventoryItem aggregates,
 * and persists them using the repository.
 * </p>
 *
 * @author Author
 */
@Service
public class InventoryItemCommandServiceImpl implements InventoryItemCommandService {

    private final InventoryItemRepository inventoryItemRepository;

    /**
     * Constructs the InventoryItemCommandServiceImpl with the given repository.
     *
     * @param inventoryItemRepository Repository for InventoryItem persistence.
     */
    public InventoryItemCommandServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    /**
     * Handles the creation of an InventoryItem from the provided command.
     * <p>
     * Validates that no other InventoryItem exists with the same SKU identifier,
     * creates the aggregate, and saves it to the database.
     * </p>
     *
     * @param command Command containing SKU identifier, minimum quantity, and available quantity.
     * @return An Optional containing the newly created InventoryItem.
     * @throws InventoryItemAlreadyExistsException if an InventoryItem with the same SKU already exists.
     * @throws IllegalArgumentException if an error occurs while saving.
     */
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
