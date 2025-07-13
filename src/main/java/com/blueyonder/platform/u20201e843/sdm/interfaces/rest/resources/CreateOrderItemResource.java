package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources;

import java.util.Date;

/**
 * REST resource representing the request payload for creating a new OrderItem.
 * <p>
 * Carries the data required by the API to register an OrderItem,
 * including SKU identifier, requested quantity, and order date.
 * </p>
 *
 * @param skuIdentifier     The SKU identifier of the item (UUID string).
 * @param requestedQuantity The quantity requested for this order item (must be greater than zero).
 * @param orderedAt         The date and time the order was placed (cannot be in the future).
 *
 * @author Author
 */
public record CreateOrderItemResource(
        String skuIdentifier,
        Double requestedQuantity,
        Date orderedAt
) {
}
