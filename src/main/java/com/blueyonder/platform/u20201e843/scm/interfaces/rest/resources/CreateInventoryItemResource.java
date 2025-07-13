package com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources;

/**
 * REST resource representing the request payload for creating a new InventoryItem.
 * <p>
 * Carries the data required to register an InventoryItem via the API.
 * </p>
 *
 * @param skuIdentifier     Unique business identifier (must be a valid UUID string).
 * @param minimumQuantity   Minimum threshold quantity (must be greater than 10).
 * @param availableQuantity Initial available quantity (must be at least three times the minimum).
 *
 * @author Author
 */
public record CreateInventoryItemResource(
        String skuIdentifier,
        Double minimumQuantity,
        Double availableQuantity
) {
}
