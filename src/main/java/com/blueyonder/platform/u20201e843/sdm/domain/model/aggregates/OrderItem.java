package com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates;

import com.blueyonder.platform.u20201e843.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u20201e843.sdm.domain.model.valueobjects.OrderItemStatus;
import com.blueyonder.platform.u20201e843.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

/**
 * Aggregate root representing an OrderItem in the Sales and Distribution Management bounded context.
 * <p>
 * Captures the details of an order line, including SKU, quantity, status, and ordering date.
 * Supports status transitions based on inventory availability.
 * </p>
 *
 * @author Author
 */
@Getter
@Entity
public class OrderItem extends AuditableAbstractAggregateRoot<OrderItem> {

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

    /**
     * Default no-args constructor for JPA.
     */
    public OrderItem() {}

    /**
     * Constructs a new OrderItem based on the given command.
     *
     * @param command The command containing order details such as orderId, SKU, quantity, and orderedAt date.
     */
    public OrderItem(CreateOrderItemCommand command) {
        this.orderId = command.orderId();
        this.skuIdentifier = new SkuIdentifier(command.skuIdentifier());
        this.requestedQuantity = command.requestedQuantity();
        this.orderedAt = command.orderedAt();
    }

    /**
     * Marks the OrderItem as READY_FOR_DISPATCH.
     * Indicates that the requested quantity is available in inventory.
     */
    public void readyForDispatch() {
        this.status = OrderItemStatus.READY_FOR_DISPATCH;
    }

    /**
     * Marks the OrderItem as WAITING_FOR_INVENTORY.
     * Indicates that the requested quantity could not be fully fulfilled from stock.
     */
    public void waitingForInventory() {
        this.status = OrderItemStatus.WAITING_FOR_INVENTORY;
    }
}
