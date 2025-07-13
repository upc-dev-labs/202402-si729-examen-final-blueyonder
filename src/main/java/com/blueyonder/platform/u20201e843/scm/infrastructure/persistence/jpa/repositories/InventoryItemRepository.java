package com.blueyonder.platform.u20201e843.scm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA Repository interface for managing InventoryItem entities.
 * <p>
 * Provides methods to access Inventory Items by their SKU identifiers and
 * to check for their existence in the database.
 * </p>
 *
 * @author Author
 */
@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    /**
     * Retrieves an InventoryItem by its SKU identifier.
     *
     * @param skuIdentifier The SKU identifier to search for.
     * @return An Optional containing the InventoryItem if found, or empty if not found.
     */
    Optional<InventoryItem> findBySkuIdentifier(SkuIdentifier skuIdentifier);

    /**
     * Checks if an InventoryItem exists with the given SKU identifier.
     *
     * @param skuIdentifier The SKU identifier to check.
     * @return true if an InventoryItem with the given SKU exists, false otherwise.
     */
    boolean existsBySkuIdentifier(SkuIdentifier skuIdentifier);
}
