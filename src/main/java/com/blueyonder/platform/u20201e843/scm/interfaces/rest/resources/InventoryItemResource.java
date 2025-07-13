package com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources;

/**
 * REST resource representing the InventoryItem data exposed by the API.
 * <p>
 * Used for returning detailed information about an InventoryItem
 * in HTTP responses.
 * </p>
 *
 * @param id                 Unique database identifier of the InventoryItem.
 * @param skuIdentifier      Business SKU identifier (UUID as String).
 * @param status             Current inventory status (e.g., WITH_STOCK, UNDER_MINIMUM).
 * @param minimumQuantity    Minimum threshold quantity for triggering supply orders.
 * @param availableQuantity  Quantity currently available for dispatch.
 * @param reservedQuantity   Quantity currently reserved for orders.
 * @param pendingSupplyQuantity Quantity pending in supply orders.
 *
 * @author Author
 */
public record InventoryItemResource(
        Long id,
        String skuIdentifier,
        String status,
        Double minimumQuantity,
        Double availableQuantity,
        Double reservedQuantity,
        Double pendingSupplyQuantity
) {
}
