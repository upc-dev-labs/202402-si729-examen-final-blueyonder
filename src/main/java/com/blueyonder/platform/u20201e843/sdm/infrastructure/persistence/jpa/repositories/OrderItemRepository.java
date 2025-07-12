package com.blueyonder.platform.u20201e843.sdm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u20201e843.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    boolean existsByOrderIdAndSkuIdentifier(Long orderId, SkuIdentifier skuIdentifier);
}
