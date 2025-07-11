package com.blueyonder.platform.u20201e843.scm.infrastructure.persistence.jpa.converters;

import com.blueyonder.platform.u20201e843.scm.domain.model.valueobjects.InventoryItemStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class InventoryItemStatusConverter implements AttributeConverter<InventoryItemStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InventoryItemStatus attribute) {
        if (attribute == null) return null;
        return attribute.getId();
    }

    @Override
    public InventoryItemStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return InventoryItemStatus.fromId(dbData);
    }
}
