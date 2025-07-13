package com.blueyonder.platform.u20201e843.scm.domain.model.events;

import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Domain event indicating that the available quantity of an InventoryItem
 * has fallen below its minimum threshold.
 *
 * @author Author
 */
@Getter
public class MinimumQuantityThresholdReachedEvent extends ApplicationEvent {

    /**
     * The SKU identifier of the InventoryItem that reached the threshold.
     */
    private final SkuIdentifier skuIdentifier;

    /**
     * The requested supply quantity calculated as minimum plus pending supply.
     */
    private final Double requestedSupplyQuantity;

    /**
     * Constructs a new MinimumQuantityThresholdReachedEvent.
     *
     * @param source                  The object on which the event initially occurred.
     * @param skuIdentifier           The SKU identifier of the InventoryItem.
     * @param requestedSupplyQuantity The quantity needed to restore stock to safe levels.
     */
    public MinimumQuantityThresholdReachedEvent(Object source, SkuIdentifier skuIdentifier, Double requestedSupplyQuantity) {
        super(source);
        this.skuIdentifier = skuIdentifier;
        this.requestedSupplyQuantity = requestedSupplyQuantity;
    }
}
