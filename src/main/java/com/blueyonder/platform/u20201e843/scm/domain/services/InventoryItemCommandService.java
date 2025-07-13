package com.blueyonder.platform.u20201e843.scm.domain.services;

import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.scm.domain.model.commands.CreateInventoryItemCommand;

import java.util.Optional;

/**
 * Domain service interface for handling InventoryItem creation commands.
 * <p>
 * Defines the contract for processing CreateInventoryItemCommand
 * and producing a persisted InventoryItem if successful.
 * </p>
 *
 * @author Author
 */
public interface InventoryItemCommandService {

    /**
     * Handles the creation of an InventoryItem based on the given command.
     *
     * @param command The command containing SKU identifier, minimum quantity, and available quantity.
     * @return An Optional containing the created InventoryItem, or empty if creation fails.
     */
    Optional<InventoryItem> handle(CreateInventoryItemCommand command);
}
