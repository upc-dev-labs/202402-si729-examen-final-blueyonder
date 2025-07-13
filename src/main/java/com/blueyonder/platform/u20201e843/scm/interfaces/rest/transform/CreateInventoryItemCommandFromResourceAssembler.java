package com.blueyonder.platform.u20201e843.scm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources.CreateInventoryItemResource;

/**
 * Assembler utility for converting REST resources into domain commands.
 * <p>
 * Transforms CreateInventoryItemResource received via HTTP requests
 * into CreateInventoryItemCommand for use in the application layer.
 * </p>
 *
 * @author Author
 */
public class CreateInventoryItemCommandFromResourceAssembler {

    /**
     * Converts a CreateInventoryItemResource into a CreateInventoryItemCommand.
     *
     * @param resource The REST resource containing SKU identifier, minimum quantity, and available quantity.
     * @return A CreateInventoryItemCommand with the same data.
     */
    public static CreateInventoryItemCommand toCommandFromResource(CreateInventoryItemResource resource) {
        return new CreateInventoryItemCommand(
                resource.skuIdentifier(),
                resource.minimumQuantity(),
                resource.availableQuantity()
        );
    }
}
