package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources;

import java.util.Date;

public record CreateOrderItemResource(
        String skuIdentifier,
        Double requestedQuantity,
        Date orderedAt
) {
}
