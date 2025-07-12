package com.blueyonder.platform.u20201e843.scm.domain.model.events;

import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MinimumQuantityThresholdReachedEvent extends ApplicationEvent {

    private final SkuIdentifier skuIdentifier;
    private final Double requestedSupplyQuantity;

    public MinimumQuantityThresholdReachedEvent(Object source, SkuIdentifier skuIdentifier, Double requestedSupplyQuantity) {
        super(source);
        this.skuIdentifier = skuIdentifier;
        this.requestedSupplyQuantity = requestedSupplyQuantity;
    }
}
