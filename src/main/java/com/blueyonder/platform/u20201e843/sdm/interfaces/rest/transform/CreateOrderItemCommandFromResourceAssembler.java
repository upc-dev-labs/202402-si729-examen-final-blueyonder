package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources.CreateOrderItemResource;

public class CreateOrderItemCommandFromResourceAssembler {
    public static CreateOrderItemCommand toCommandFromResource(Long orderId, CreateOrderItemResource resource) {
        return new CreateOrderItemCommand(
                orderId,
                resource.skuIdentifier(),
                resource.requestedQuantity(),
                resource.orderedAt()
        );
    }
}
