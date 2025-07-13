package com.blueyonder.platform.u20201e843.scm.interfaces.acl;

/**
 * Anti-Corruption Layer (ACL) interface exposing SCM capabilities
 * to other bounded contexts.
 * <p>
 * Defines operations for checking inventory item existence and
 * verifying dispatch readiness by SKU identifier.
 * </p>
 *
 * @author Author
 */
public interface ScmContextFacade {

    /**
     * Checks if an InventoryItem exists with the given SKU identifier.
     *
     * @param skuIdentifier The SKU identifier to check.
     * @return true if an InventoryItem with the given SKU exists, false otherwise.
     */
    boolean existsInventoryItemBySkuIdentifier(String skuIdentifier);

    /**
     * Determines if a dispatch request can be fully satisfied
     * for the given SKU identifier and requested quantity.
     *
     * @param skuIdentifier     The SKU identifier of the inventory item.
     * @param requestedQuantity The quantity requested for dispatch.
     * @return true if the requested quantity can be fully dispatched, false otherwise.
     */
    boolean isReadyForDispatch(String skuIdentifier, Double requestedQuantity);
}
