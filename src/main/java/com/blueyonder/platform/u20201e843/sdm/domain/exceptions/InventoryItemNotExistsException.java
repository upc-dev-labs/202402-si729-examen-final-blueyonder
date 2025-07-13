package com.blueyonder.platform.u20201e843.sdm.domain.exceptions;

/**
 * Exception thrown when attempting to create an OrderItem
 * referencing an InventoryItem that does not exist in the system.
 * <p>
 * Enforces the business rule that an OrderItem can only be created
 * if its associated InventoryItem is present.
 * </p>
 *
 * @author Author
 */
public class InventoryItemNotExistsException extends RuntimeException {

    /**
     * Constructs a new exception with a message indicating the missing SKU.
     *
     * @param skuIdentifier The SKU identifier that does not exist.
     */
    public InventoryItemNotExistsException(String skuIdentifier) {
        super("Inventory item with SKU identifier %s does not exist.".formatted(skuIdentifier));
    }
}
