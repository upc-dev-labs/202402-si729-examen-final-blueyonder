package com.blueyonder.platform.u20201e843.scm.interfaces.acl;

public interface ScmContextFacade {

    boolean existsInventoryItemBySkuIdentifier(String skuIdentifier);

    boolean isReadyForDispatch(String skuIdentifier, Double requestedQuantity);
}
