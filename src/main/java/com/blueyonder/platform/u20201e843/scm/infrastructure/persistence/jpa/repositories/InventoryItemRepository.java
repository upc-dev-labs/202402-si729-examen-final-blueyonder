package com.blueyonder.platform.u20201e843.scm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findBySkuIdentifier(SkuIdentifier skuIdentifier);

    boolean existsBySkuIdentifier(SkuIdentifier skuIdentifier);
}
