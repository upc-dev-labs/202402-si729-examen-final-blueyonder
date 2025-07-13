package com.blueyonder.platform.u20201e843.scm.application.acl;

import com.blueyonder.platform.u20201e843.scm.domain.exceptions.InventoryItemNotFoundException;
import com.blueyonder.platform.u20201e843.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import com.blueyonder.platform.u20201e843.scm.interfaces.acl.ScmContextFacade;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.stereotype.Service;

/**
 * Application-level implementation of the ScmContextFacade ACL.
 * <p>
 * Provides access to SCM bounded context capabilities such as checking
 * InventoryItem existence and evaluating dispatch readiness.
 * </p>
 *
 * @author Author
 */
@Service
public class ScmContextFacadeImpl implements ScmContextFacade {

    private final InventoryItemRepository inventoryItemRepository;

    /**
     * Constructs the ACL implementation with the given InventoryItemRepository.
     *
     * @param inventoryItemRepository Repository for accessing InventoryItem data.
     */
    public ScmContextFacadeImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    /**
     * Checks if an InventoryItem exists in the SCM bounded context
     * with the given SKU identifier.
     *
     * @param skuIdentifier The SKU identifier to check.
     * @return true if the InventoryItem exists, false otherwise.
     */
    @Override
    public boolean existsInventoryItemBySkuIdentifier(String skuIdentifier) {
        return inventoryItemRepository.existsBySkuIdentifier(new SkuIdentifier(skuIdentifier));
    }

    /**
     * Determines if the requested quantity for a given SKU can be fully dispatched.
     * <p>
     * Applies business rules on the InventoryItem and persists any changes
     * such as stock reductions or event registrations.
     * </p>
     *
     * @param skuIdentifier     The SKU identifier of the InventoryItem.
     * @param requestedQuantity The quantity requested for dispatch.
     * @return true if the dispatch can be fully satisfied, false if there is a shortage.
     * @throws InventoryItemNotFoundException if the InventoryItem with the given SKU does not exist.
     */
    @Override
    public boolean isReadyForDispatch(String skuIdentifier, Double requestedQuantity) {
        var inventoryItem = inventoryItemRepository.findBySkuIdentifier(new SkuIdentifier(skuIdentifier))
                .orElseThrow(() -> new InventoryItemNotFoundException(skuIdentifier));

        var isReady = inventoryItem.processDispatchRequest(requestedQuantity);

        inventoryItemRepository.save(inventoryItem);

        return isReady;
    }
}
