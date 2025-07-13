package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources.OrderItemResource;

public class OrderItemResourceFromEntityAssembler {
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
