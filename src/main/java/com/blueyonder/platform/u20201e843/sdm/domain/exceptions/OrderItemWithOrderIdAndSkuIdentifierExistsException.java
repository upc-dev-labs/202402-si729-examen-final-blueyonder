package com.blueyonder.platform.u20201e843.sdm.domain.exceptions;

public class OrderItemWithOrderIdAndSkuIdentifierExistsException extends RuntimeException {

    public OrderItemWithOrderIdAndSkuIdentifierExistsException(Long orderId, String skuIdentifier) {
        super("Order Item with order id %s and sku identifier %s already exists.".formatted(orderId, skuIdentifier));
    }
}
