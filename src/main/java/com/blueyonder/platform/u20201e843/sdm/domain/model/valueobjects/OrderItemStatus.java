package com.blueyonder.platform.u20201e843.sdm.domain.model.valueobjects;

import lombok.Getter;

/**
 * Order item status with numeric ID mapping.
 * <p>
 * Provides utility methods for mapping from ID or name.
 *
 * @author Author
 * @since 1.0
 */
@Getter
public enum OrderItemStatus {
    READY_FOR_DISPATCH(0, "READY_FOR_DISPATCH"),
    WAITING_FOR_INVENTORY(1, "WAITING_FOR_INVENTORY"),
    DISPATCHING(2, "DISPATCHING"),
    COMPLETED(3, "COMPLETED");

    private final int id;

    private final String label;

    OrderItemStatus(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static OrderItemStatus fromId(int id) {
        for (OrderItemStatus status : OrderItemStatus.values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid OrderItemStatus id: " + id);
    }

    public static OrderItemStatus fromLabel(String label) {
        label = label.toUpperCase();
        for (OrderItemStatus status : OrderItemStatus.values()) {
            if (status.label.equals(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid OrderItemStatus label: " + label);
    }
}
