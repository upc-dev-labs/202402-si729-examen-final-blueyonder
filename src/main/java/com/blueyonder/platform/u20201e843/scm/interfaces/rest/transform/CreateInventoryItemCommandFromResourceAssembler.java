package com.blueyonder.platform.u20201e843.scm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources.CreateInventoryItemResource;

public class CreateInventoryItemCommandFromResourceAssembler {
    public static CreateInventoryItemCommand toCommandFromResource(CreateInventoryItemResource resource) {
        return new CreateInventoryItemCommand(
                resource.skuIdentifier(),
                resource.minimumQuantity(),
                resource.availableQuantity()
        );
    }
}
