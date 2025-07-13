package com.blueyonder.platform.u20201e843.sdm.application.internal.outboundservices.acl;

import com.blueyonder.platform.u20201e843.scm.interfaces.acl.ScmContextFacade;
import org.springframework.stereotype.Service;

/**
 * Outbound service acting as an Anti-Corruption Layer (ACL) for accessing
 * SCM bounded context capabilities from SDM.
 * <p>
 * Delegates calls to the ScmContextFacade interface to decouple
 * the SDM bounded context from direct dependency on SCM internals.
 * </p>
 *
 * @author Author
 */
@Service
public class ExternalScmService {

    private final ScmContextFacade scmContextFacade;

    /**
     * Constructs the ExternalScmService with the given ScmContextFacade.
     *
     * @param scmContextFacade The interface providing access to SCM capabilities.
     */
    public ExternalScmService(ScmContextFacade scmContextFacade) {
        this.scmContextFacade = scmContextFacade;
    }

    /**
     * Checks if an InventoryItem exists in the SCM bounded context
     * with the given SKU identifier.
     *
     * @param skuIdentifier The SKU identifier to check.
     * @return true if the InventoryItem exists in SCM, false otherwise.
     */
    public boolean existsInventoryItemBySkuIdentifier(String skuIdentifier) {
        return scmContextFacade.existsInventoryItemBySkuIdentifier(skuIdentifier);
    }

    /**
     * Determines if the requested quantity for a given SKU can be fully dispatched
     * according to the SCM bounded context.
     *
     * @param skuIdentifier     The SKU identifier of the inventory item.
     * @param requestedQuantity The quantity requested for dispatch.
     * @return true if the dispatch can be fully satisfied, false otherwise.
     */
    public boolean isReadyForDispatch(String skuIdentifier, Double requestedQuantity) {
        return scmContextFacade.isReadyForDispatch(skuIdentifier, requestedQuantity);
    }
}
