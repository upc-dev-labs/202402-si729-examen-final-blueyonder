package com.blueyonder.platform.u20201e843.sdm.domain.exceptions;

public class InventoryItemNotExistsException extends RuntimeException {

    public InventoryItemNotExistsException(String skuIdentifier) {
        super("Inventory item with SKU identifier %s does not exist.".formatted(skuIdentifier));
    }
}
