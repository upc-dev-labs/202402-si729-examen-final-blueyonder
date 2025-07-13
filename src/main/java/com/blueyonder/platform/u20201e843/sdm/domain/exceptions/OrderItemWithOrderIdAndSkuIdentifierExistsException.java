package com.blueyonder.platform.u20201e843.sdm.domain.exceptions;

/**
 * Exception thrown when attempting to create an OrderItem with
 * a combination of orderId and SKU identifier that already exists.
 * <p>
 * Enforces the business rule that an Order cannot contain
 * duplicate items with the same SKU.
 * </p>
 *
 * @author Author
 */
public class OrderItemWithOrderIdAndSkuIdentifierExistsException extends RuntimeException {

    /**
     * Constructs a new exception indicating the duplicate orderId and SKU identifier.
     *
     * @param orderId       The ID of the order.
     * @param skuIdentifier The SKU identifier that is duplicated.
     */
    public OrderItemWithOrderIdAndSkuIdentifierExistsException(Long orderId, String skuIdentifier) {
        super("Order Item with order id %s and sku identifier %s already exists.".formatted(orderId, skuIdentifier));
    }
}
