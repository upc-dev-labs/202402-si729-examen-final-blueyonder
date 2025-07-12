package com.blueyonder.platform.u20201e843.scm.domain.exceptions;

public class InventoryItemAlreadyExistsException extends RuntimeException {

    public InventoryItemAlreadyExistsException(String skuIdentifier) {
        super("Inventory item with sku identifier %s already exists.".formatted(skuIdentifier));
    }
}
