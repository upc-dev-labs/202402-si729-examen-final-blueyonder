package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources.CreateOrderItemResource;

/**
 * Assembler utility for converting REST resources into domain commands.
 * <p>
 * Transforms CreateOrderItemResource objects received from the API
 * into CreateOrderItemCommand instances for use in the application layer.
 * </p>
 *
 * @author Author
 */
public class CreateOrderItemCommandFromResourceAssembler {

    /**
     * Converts a CreateOrderItemResource and orderId into a CreateOrderItemCommand.
     *
     * @param orderId  The ID of the order this item belongs to.
     * @param resource The REST resource containing SKU identifier, requested quantity, and ordered date.
     * @return A CreateOrderItemCommand initialized with data from the resource.
     */
    public static CreateOrderItemCommand toCommandFromResource(Long orderId, CreateOrderItemResource resource) {
        return new CreateOrderItemCommand(
                orderId,
                resource.skuIdentifier(),
                resource.requestedQuantity(),
                resource.orderedAt()
        );
    }
}
