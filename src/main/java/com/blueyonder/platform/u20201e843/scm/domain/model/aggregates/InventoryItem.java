package com.blueyonder.platform.u20201e843.scm.domain.model.aggregates;

import com.blueyonder.platform.u20201e843.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class InventoryItem extends AuditableAbstractAggregateRoot<InventoryItem> {


}
