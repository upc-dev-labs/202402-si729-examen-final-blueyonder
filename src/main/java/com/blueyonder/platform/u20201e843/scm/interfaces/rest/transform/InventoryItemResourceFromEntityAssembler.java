package com.blueyonder.platform.u20201e843.scm.interfaces.rest.transform;

import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources.InventoryItemResource;

/**
 * Assembler utility for converting InventoryItem domain entities into
 * InventoryItemResource representations for the REST layer.
 * <p>
 * Responsible for translating domain data into a format suitable
 * for API responses.
 * </p>
 *
 * @author Author
 */
public class InventoryItemResourceFromEntityAssembler {

    /**
     * Converts an InventoryItem domain entity into an InventoryItemResource.
     *
     * @param entity The InventoryItem entity to convert.
     * @return A REST resource representation of the InventoryItem.
     */
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
