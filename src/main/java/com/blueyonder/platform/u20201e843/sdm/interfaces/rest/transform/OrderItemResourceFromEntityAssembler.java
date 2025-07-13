package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources.OrderItemResource;

/**
 * Assembler utility for converting domain OrderItem entities into REST resources.
 * <p>
 * Transforms OrderItem aggregates into OrderItemResource representations
 * suitable for exposure via the API.
 * </p>
 *
 * @author Author
 */
public class OrderItemResourceFromEntityAssembler {

    /**
     * Converts an OrderItem domain entity into an OrderItemResource for the REST layer.
     *
     * @param entity The OrderItem entity to convert.
     * @return A REST resource representation of the OrderItem.
     */
    public static OrderItemResource toResourceFromEntity(OrderItem entity) {
        return new OrderItemResource(
                entity.getId(),
                entity.getOrderId(),
                entity.getSkuIdentifier().skuIdentifier(),
                entity.getRequestedQuantity(),
                entity.getStatus().getLabel(),
                entity.getOrderedAt()
        );
    }
}
