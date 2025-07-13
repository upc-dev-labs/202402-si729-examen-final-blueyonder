package com.blueyonder.platform.u20201e843.scm.domain.model.commands;

/**
 * Command representing the data required to create an Inventory Item.
 * <p>
 * Enforces business rules at creation time to ensure data validity.
 * </p>
 *
 * @param skuIdentifier     Unique SKU identifier as String (must be a valid UUID).
 * @param minimumQuantity   Minimum threshold quantity (must be greater than 10).
 * @param availableQuantity Available stock (must be at least three times the minimum quantity).
 *
 * @author Author
 */
public record CreateInventoryItemCommand(
        String skuIdentifier,
        Double minimumQuantity,
        Double availableQuantity
) {
    /**
     * Compact constructor with business validations.
     *
     * @throws IllegalArgumentException if minimumQuantity or availableQuantity violate business rules.
     */
    public CreateInventoryItemCommand {
        if (minimumQuantity == null || minimumQuantity <= 10)
            throw new IllegalArgumentException("Minimum quantity must be greater than 10");
        if (availableQuantity == null || availableQuantity < (minimumQuantity * 3))
            throw new IllegalArgumentException("Available quantity must be greater than the triple of minimum quantity");
    }
}
