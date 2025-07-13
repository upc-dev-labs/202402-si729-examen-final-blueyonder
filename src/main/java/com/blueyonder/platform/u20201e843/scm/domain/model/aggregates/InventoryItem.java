package com.blueyonder.platform.u20201e843.scm.domain.model.aggregates;

import com.blueyonder.platform.u20201e843.scm.domain.model.events.MinimumQuantityThresholdReachedEvent;
import com.blueyonder.platform.u20201e843.scm.domain.model.valueobjects.InventoryItemStatus;
import com.blueyonder.platform.u20201e843.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class InventoryItem extends AuditableAbstractAggregateRoot<InventoryItem> {

    @Embedded
    @NotNull
    @Column(nullable = false)
    private SkuIdentifier skuIdentifier;

    @NotNull
    @Column(nullable = false)
    private InventoryItemStatus status;

    @NotNull
    @Column(nullable = false)
    private Double minimumQuantity;

    @NotNull
    @Column(nullable = false)
    private Double availableQuantity;

    @Column(nullable = false)
    private Double reservedQuantity;

    @Column(nullable = false)
    private Double pendingSupplyQuantity;

    public InventoryItem() {}

    public InventoryItem(String skuIdentifier, Double minimumQuantity,  Double availableQuantity) {
        this.skuIdentifier = new SkuIdentifier(skuIdentifier);
        this.minimumQuantity = minimumQuantity;
        this.availableQuantity = availableQuantity;
        this.status = InventoryItemStatus.WITH_STOCK;
        this.reservedQuantity = 0.0;
        this.pendingSupplyQuantity = 0.0;
    }

    public boolean processDispatchRequest(Double requestedQuantity) {
        if (this.availableQuantity >= requestedQuantity) {
            this.availableQuantity -= requestedQuantity;
            this.reservedQuantity += requestedQuantity;
            this.updateAvailableQuantity(this.availableQuantity);
            return true;
        } else {
            var shortage = requestedQuantity - this.availableQuantity;
            this.pendingSupplyQuantity += shortage;
            this.availableQuantity = 0.0;
            this.updateAvailableQuantity(this.availableQuantity);
            return false;
        }
    }

    public void updateAvailableQuantity(Double availableQuantity) {
        this.availableQuantity = availableQuantity;

        if (this.availableQuantity < this.minimumQuantity)
            this.reachMinimumQuantityThreshold();
    }

    public void reachMinimumQuantityThreshold() {
        status = InventoryItemStatus.UNDER_MINIMUM;
        this.registerEvent(new MinimumQuantityThresholdReachedEvent(
                this,
                this.skuIdentifier,
                this.minimumQuantity + this.pendingSupplyQuantity)
        );
    }
}
