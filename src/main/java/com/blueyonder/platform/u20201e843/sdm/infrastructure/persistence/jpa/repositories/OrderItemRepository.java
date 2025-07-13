package com.blueyonder.platform.u20201e843.sdm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository interface for managing OrderItem entities.
 * <p>
 * Provides methods to persist, retrieve, and check the existence of
 * OrderItems based on order ID and SKU identifier.
 * </p>
 *
 * @author Author
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * Checks whether an OrderItem exists with the given order ID and SKU identifier.
     *
     * @param orderId       The ID of the order.
     * @param skuIdentifier The SKU identifier of the item.
     * @return true if an OrderItem with the specified orderId and SKU exists, false otherwise.
     */
    boolean existsByOrderIdAndSkuIdentifier(Long orderId, SkuIdentifier skuIdentifier);
}
