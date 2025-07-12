package com.blueyonder.platform.u20201e843.scm.domain.services;

import com.blueyonder.platform.u20201e843.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u20201e843.scm.domain.model.commands.CreateInventoryItemCommand;

import java.util.Optional;

public interface InventoryItemCommandService {

    Optional<InventoryItem> handle(CreateInventoryItemCommand command);
}
