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

/**
 * Aggregate root representing an Inventory Item in the Supply Chain Management bounded context.
 * <p>
 * Manages the stock levels and status of a product identified by its SKU.
 * Handles business rules such as dispatch processing and threshold checks.
 * </p>
 *
 * @author Author
 */
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

    /**
     * Default constructor for JPA.
     */
    public InventoryItem() {}

    /**
     * Constructs an InventoryItem with initial values.
     *
     * @param skuIdentifier      SKU identifier of the product.
     * @param minimumQuantity    Minimum threshold quantity.
     * @param availableQuantity  Initial available stock quantity.
     */
    public InventoryItem(String skuIdentifier, Double minimumQuantity,  Double availableQuantity) {
        this.skuIdentifier = new SkuIdentifier(skuIdentifier);
        this.minimumQuantity = minimumQuantity;
        this.availableQuantity = availableQuantity;
        this.status = InventoryItemStatus.WITH_STOCK;
        this.reservedQuantity = 0.0;
        this.pendingSupplyQuantity = 0.0;
    }

    /**
     * Processes a dispatch request by adjusting stock levels.
     * Handles both full and partial dispatch scenarios.
     * Checks if the minimum threshold is breached and emits an event if necessary.
     *
     * @param requestedQuantity Quantity requested for dispatch.
     * @return true if the dispatch can be fully satisfied from available stock, false if there is a shortage.
     */
    public boolean processDispatchRequest(Double requestedQuantity) {
        this.applyDispatchRequest(requestedQuantity);
        this.ensureMinimumQuantityThreshold();
        return this.availableQuantity >= requestedQuantity;
    }

    /**
     * Marks this InventoryItem as UNDER_MINIMUM and emits a MinimumQuantityThresholdReachedEvent.
     */
    public void reachMinimumQuantityThreshold() {
        status = InventoryItemStatus.UNDER_MINIMUM;
        this.registerEvent(new MinimumQuantityThresholdReachedEvent(
                this,
                this.skuIdentifier,
                this.minimumQuantity + this.pendingSupplyQuantity)
        );
    }

    /**
     * Applies the stock adjustments for a dispatch request.
     * Handles both full and partial dispatch cases.
     *
     * @param requestedQuantity Quantity requested for dispatch.
     */
    private void applyDispatchRequest(Double requestedQuantity) {
        if (this.availableQuantity >= requestedQuantity) {
            this.availableQuantity -= requestedQuantity;
            this.reservedQuantity += requestedQuantity;
        } else {
            double shortage = requestedQuantity - this.availableQuantity;
            this.pendingSupplyQuantity += shortage;
            this.reservedQuantity += this.availableQuantity;
            this.availableQuantity = 0.0;
        }
    }

    /**
     * Checks if the available quantity is below the minimum threshold
     * and triggers the threshold event if necessary.
     */
    private void ensureMinimumQuantityThreshold() {
        if (this.availableQuantity < this.minimumQuantity) {
            this.reachMinimumQuantityThreshold();
        }
    }
}
