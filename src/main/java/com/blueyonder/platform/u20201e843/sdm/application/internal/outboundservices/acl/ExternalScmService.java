package com.blueyonder.platform.u20201e843.sdm.application.internal.outboundservices.acl;

import com.blueyonder.platform.u20201e843.scm.interfaces.acl.ScmContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalScmService {

    private final ScmContextFacade scmContextFacade;

    public ExternalScmService(ScmContextFacade scmContextFacade) {
        this.scmContextFacade = scmContextFacade;
    }

    public boolean existsInventoryItemBySkuIdentifier(String skuIdentifier) {
        return scmContextFacade.existsInventoryItemBySkuIdentifier(skuIdentifier);
    }

    public boolean isReadyForDispatch(String skuIdentifier, Double requestedQuantity) {
        return scmContextFacade.isReadyForDispatch(skuIdentifier, requestedQuantity);
    }
}
