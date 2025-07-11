package com.blueyonder.platform.u20201e843.sdm.infrastructure.persistence.jpa.converters;

import com.blueyonder.platform.u20201e843.sdm.domain.model.valueobjects.OrderItemStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA converter for mapping status enum to its numeric ID.
 * <p>
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
