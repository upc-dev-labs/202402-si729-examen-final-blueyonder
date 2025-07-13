package com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources;

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
