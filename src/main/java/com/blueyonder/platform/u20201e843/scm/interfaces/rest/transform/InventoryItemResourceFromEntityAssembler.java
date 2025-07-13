package com.blueyonder.platform.u20201e843.scm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources.InventoryItemResource;

public class InventoryItemResourceFromEntityAssembler {
    public static InventoryItemResource toResourceFromEntity(InventoryItem entity) {
        return new InventoryItemResource(
                entity.getId(),
                entity.getSkuIdentifier().skuIdentifier(),
                entity.getStatus().getLabel(),
                entity.getMinimumQuantity(),
                entity.getAvailableQuantity(),
                entity.getReservedQuantity(),
                entity.getPendingSupplyQuantity()
        );
    }
}
