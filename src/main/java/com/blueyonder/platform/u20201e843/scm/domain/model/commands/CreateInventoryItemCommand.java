package com.blueyonder.platform.u20201e843.scm.domain.model.commands;

public record CreateInventoryItemCommand(
        String skuIdentifier,
        Double minimumQuantity,
        Double availableQuantity
) {
    public CreateInventoryItemCommand {
        if (minimumQuantity == null || minimumQuantity <= 10)
            throw new IllegalArgumentException("Minimum quantity must be greater than 10");
        if (availableQuantity == null || availableQuantity < (minimumQuantity * 3))
            throw new IllegalArgumentException("Available quantity must be greater than the triple of minimum quantity");
    }
}
