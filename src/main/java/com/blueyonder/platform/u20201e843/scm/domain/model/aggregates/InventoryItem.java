package com.blueyonder.platform.u20201e843.scm.domain.model.aggregates;

import com.blueyonder.platform.u20201e843.scm.domain.model.valueobjects.InventoryItemStatus;
import com.blueyonder.platform.u20201e843.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    @NotNull
    @Column(nullable = false)
    private Double availableQuantity;

    @Setter
    @Column(nullable = false)
    private Double reservedQuantity;

    @Setter
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
}
