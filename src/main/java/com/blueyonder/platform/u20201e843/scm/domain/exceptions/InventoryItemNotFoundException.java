package com.blueyonder.platform.u20201e843.scm.domain.exceptions;

/**
 * Exception thrown when an InventoryItem with a given SKU identifier
 * is not found in the system.
 * <p>
 * Used to enforce the business rule that lookups must succeed
 * only when the item exists.
 * </p>
 *
 * @author Author
 */
public class InventoryItemNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a message indicating the missing SKU.
     *
     * @param skuIdentifier The SKU identifier that was not found.
     */
    public InventoryItemNotFoundException(String skuIdentifier) {
        super("Inventory item with SKU identifier %s not found.".formatted(skuIdentifier));
    }
}
