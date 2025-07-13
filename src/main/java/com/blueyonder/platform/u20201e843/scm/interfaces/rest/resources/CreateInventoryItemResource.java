package com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources;

public record CreateInventoryItemResource(
        String skuIdentifier,
        Double minimumQuantity,
        Double availableQuantity
) {
}
