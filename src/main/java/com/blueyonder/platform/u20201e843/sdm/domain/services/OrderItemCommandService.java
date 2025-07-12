package com.blueyonder.platform.u20201e843.sdm.domain.services;

import com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u20201e843.sdm.domain.model.commands.CreateOrderItemCommand;

import java.util.Optional;

public interface OrderItemCommandService {

    Optional<OrderItem> handle(CreateOrderItemCommand command);
}
