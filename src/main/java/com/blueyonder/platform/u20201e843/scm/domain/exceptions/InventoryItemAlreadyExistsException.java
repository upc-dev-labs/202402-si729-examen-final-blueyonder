package com.blueyonder.platform.u20201e843.scm.domain.exceptions;

/**
 * Exception thrown when attempting to create an InventoryItem
 * with a SKU identifier that already exists in the system.
 * <p>
 * Enforces the business rule that SKU identifiers must be unique.
 * </p>
 *
 * @author Author
 */
public class InventoryItemAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception with a message indicating the duplicate SKU.
     *
     * @param skuIdentifier The SKU identifier that already exists.
     */
    public InventoryItemAlreadyExistsException(String skuIdentifier) {
        super("Inventory item with SKU identifier %s already exists.".formatted(skuIdentifier));
    }
}
