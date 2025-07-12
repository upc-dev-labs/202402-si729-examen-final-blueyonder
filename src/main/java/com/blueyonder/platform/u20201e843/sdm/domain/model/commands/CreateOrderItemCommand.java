package com.blueyonder.platform.u20201e843.sdm.domain.model.commands;

import java.util.Date;

public record CreateOrderItemCommand(
        Long orderId,
        String skuIdentifier,
        Double requestedQuantity,
        Date orderedAt
) {
    public CreateOrderItemCommand {
        if (orderId == null || orderId <= 0)
            throw new IllegalArgumentException("Order id must be greater than 0.");
        if (requestedQuantity == null || requestedQuantity <= 0)
            throw new IllegalArgumentException("Requested quantity must be greater than 0.");
        if (orderedAt == null || orderedAt.after(new Date())) {
            throw new IllegalArgumentException("Order date cannot be in the future.");
        }
    }
}
