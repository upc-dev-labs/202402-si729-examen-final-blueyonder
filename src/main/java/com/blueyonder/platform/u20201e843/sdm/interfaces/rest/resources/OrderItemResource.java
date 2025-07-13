package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources;

import java.util.Date;

public record OrderItemResource(
        Long id,
        Long orderId,
        String skuIdentifier,
        Double requestedQuantity,
        String status,
        Date orderedAt
) {
}
