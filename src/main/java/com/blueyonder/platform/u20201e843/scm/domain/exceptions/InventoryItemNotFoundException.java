package com.blueyonder.platform.u20201e843.scm.domain.exceptions;

public class InventoryItemNotFoundException extends RuntimeException {

    public InventoryItemNotFoundException(String skuIdentifier) {
        super("Inventory item with SKU identifier %s not found.".formatted(skuIdentifier));
    }
}
