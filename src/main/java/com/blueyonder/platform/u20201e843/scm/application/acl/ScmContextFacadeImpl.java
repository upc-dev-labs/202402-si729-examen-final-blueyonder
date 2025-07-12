package com.blueyonder.platform.u20201e843.scm.application.acl;

import com.blueyonder.platform.u20201e843.scm.domain.exceptions.InventoryItemNotFoundException;
import com.blueyonder.platform.u20201e843.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import com.blueyonder.platform.u20201e843.scm.interfaces.acl.ScmContextFacade;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.stereotype.Service;

@Service
public class ScmContextFacadeImpl implements ScmContextFacade {

    private final InventoryItemRepository inventoryItemRepository;

    public ScmContextFacadeImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public boolean existsInventoryItemBySkuIdentifier(String skuIdentifier) {
        return inventoryItemRepository.existsBySkuIdentifier(new SkuIdentifier(skuIdentifier));
    }

    @Override
    public boolean isReadyForDispatch(String skuIdentifier, Double requestedQuantity) {
        var inventoryItem = inventoryItemRepository.findBySkuIdentifier(new SkuIdentifier(skuIdentifier));

        if (inventoryItem.isEmpty()) throw new InventoryItemNotFoundException(skuIdentifier);

        var inventoryItemEntity = inventoryItem.get();

        if (inventoryItemEntity.getAvailableQuantity() >= requestedQuantity) {
            inventoryItemEntity.setAvailableQuantity(inventoryItemEntity.getAvailableQuantity() - requestedQuantity);
            inventoryItemEntity.setReservedQuantity(inventoryItemEntity.getReservedQuantity() + requestedQuantity);
            inventoryItemRepository.save(inventoryItemEntity);
            return true;
        } else {
            inventoryItemEntity.setPendingSupplyQuantity(inventoryItemEntity.getPendingSupplyQuantity() + requestedQuantity - inventoryItemEntity.getAvailableQuantity());
            inventoryItemEntity.setReservedQuantity(inventoryItemEntity.getReservedQuantity() + inventoryItemEntity.getAvailableQuantity());
            inventoryItemEntity.setAvailableQuantity(0.0);
            return false;
        }
    }
}
