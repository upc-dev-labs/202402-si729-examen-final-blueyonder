package com.blueyonder.platform.u20201e843.sdm.infrastructure.persistence.jpa.converters;

import com.blueyonder.platform.u20201e843.sdm.domain.model.valueobjects.OrderItemStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA AttributeConverter for OrderItemStatus.
 *
 * Converts the OrderItemStatus enum to its numeric ID for database storage,
 * and restores the enum from its stored integer ID.
 *
 * Ensures consistent mapping of Order Item statuses in the database.
 *
 * Auto-applied to all OrderItemStatus fields via the @Converter(autoApply = true) annotation.
 *
 * @author Author
 * @since 1.0
 */
@Converter(autoApply = true)
public class OrderItemStatusConverter implements AttributeConverter<OrderItemStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderItemStatus attribute) {
        if (attribute == null) return null;
        return attribute.getId();
    }

    @Override
    public OrderItemStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return OrderItemStatus.fromId(dbData);
    }
}
