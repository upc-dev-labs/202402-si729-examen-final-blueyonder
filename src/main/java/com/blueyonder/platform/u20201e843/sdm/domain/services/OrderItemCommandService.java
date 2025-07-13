package com.blueyonder.platform.u20201e843.sdm.domain.services;

import com.blueyonder.platform.u20201e843.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u20201e843.sdm.domain.model.commands.CreateOrderItemCommand;

import java.util.Optional;

/**
 * Domain service interface for handling OrderItem creation commands.
 * <p>
 * Defines the contract for processing CreateOrderItemCommand
 * and producing a persisted OrderItem if successful.
 * </p>
 *
 * @author Author
 */
public interface OrderItemCommandService {

    /**
     * Handles the creation of an OrderItem based on the provided command.
     *
     * @param command The command containing order details such as orderId, SKU identifier, requested quantity, and orderedAt date.
     * @return An Optional containing the created OrderItem if successful.
     */
    Optional<OrderItem> handle(CreateOrderItemCommand command);
}
