package com.blueyonder.platform.u20201e843.scm.domain.model.valueobjects;

import lombok.Getter;

/**
 * Enumeration representing the status of an Inventory Item.
 *
 * Each status has a numeric ID and a name for identification and storage purposes.
 * The enum supports mapping from ID and name values, useful for database persistence.
 *
 * Possible values:
 * <ul>
 *     <li>0 - WITH_STOCK</li>
 *     <li>1 - UNDER_MINIMUM</li>
 *     <li>2 - AUDITING</li>
 *     <li>3 - DISABLED</li>
 * </ul>
 *
 * Used to enforce business rules related to inventory availability and auditing states.
 *
 * @author Author
 * @since 1.0
 */
@Getter
public enum InventoryItemStatus {
    WITH_STOCK(0, "WITH_STOCK"),
    UNDER_MINIMUM(1, "UNDER_MINIMUM"),
    AUDITING(2, "AUDITING"),
    DISABLED(3, "DISABLED");

    private final int id;

    private final String label;

    InventoryItemStatus(int id, String label) {
        this.id = id;
        this.label = label;
    }

    /**
     * Returns the InventoryItemStatus corresponding to the given ID.
     *
     * @param id the status ID
     * @return the matching InventoryItemStatus
     * @throws IllegalArgumentException if no matching status is found
     */
    public static InventoryItemStatus fromId(int id) {
        for (InventoryItemStatus status : InventoryItemStatus.values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid InventoryItemStatus id: " + id);
    }

    /**
     * Returns the InventoryItemStatus corresponding to the given label.
     *
     * @param label the status label (case-insensitive)
     * @return the matching InventoryItemStatus
     * @throws IllegalArgumentException if no matching status is found
     */
    public static InventoryItemStatus fromLabel(String label) {
        label = label.toUpperCase();
        for (InventoryItemStatus status : InventoryItemStatus.values()) {
            if (status.label.equals(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid InventoryItemStatus label: " + label);
    }
}
