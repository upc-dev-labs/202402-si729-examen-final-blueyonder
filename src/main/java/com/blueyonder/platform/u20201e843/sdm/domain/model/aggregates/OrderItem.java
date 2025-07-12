package com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates;

import com.blueyonder.platform.u20201e843.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u20201e843.sdm.domain.model.valueobjects.OrderItemStatus;
import com.blueyonder.platform.u20201e843.shared.domain.model.entities.AuditableModel;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class OrderItem extends AuditableModel {

    @Min(1)
    @NotNull
    @Column(nullable = false)
    private Long orderId;

    @Embedded
    @NotNull
    @Column(nullable = false)
    private SkuIdentifier skuIdentifier;

    @DecimalMin(value = "0.01", inclusive = true)
    @NotNull
    @Column(nullable = false)
    private Double requestedQuantity;

    @NotNull
    @Column(nullable = false)
    private OrderItemStatus status;

    @NotNull
    @Column(nullable = false)
    private Date orderedAt;

    public OrderItem() {}

    public OrderItem(CreateOrderItemCommand command) {
        this.orderId = command.orderId();
        this.skuIdentifier = new SkuIdentifier(command.skuIdentifier());
        this.requestedQuantity = command.requestedQuantity();
        this.orderedAt = command.orderedAt();
    }

    public void readyForDispatch() {
        this.status = OrderItemStatus.READY_FOR_DISPATCH;
    }

    public void waitingForInventory() {
        this.status = OrderItemStatus.WAITING_FOR_INVENTORY;
    }
}
