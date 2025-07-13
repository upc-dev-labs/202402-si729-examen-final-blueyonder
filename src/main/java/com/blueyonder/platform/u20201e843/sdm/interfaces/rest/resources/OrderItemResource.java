package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources;

import java.util.Date;

/**
 * REST resource representing the response payload for an OrderItem.
 * <p>
 * Exposes the details of an OrderItem as returned by the API,
 * including its identifiers, requested quantity, status, and order date.
 * </p>
 *
 * @param id                Unique identifier of the OrderItem in the system.
 * @param orderId           The ID of the order this item belongs to.
 * @param skuIdentifier     Business SKU identifier (UUID as String).
 * @param requestedQuantity Quantity requested for this order item.
 * @param status            Current status of the order item (e.g., READY_FOR_DISPATCH, WAITING_FOR_INVENTORY).
 * @param orderedAt         Date and time when the order was placed.
 *
 * @author Author
 */
public record OrderItemResource(
        Long id,
        Long orderId,
        String skuIdentifier,
        Double requestedQuantity,
        String status,
        Date orderedAt
) {
}
