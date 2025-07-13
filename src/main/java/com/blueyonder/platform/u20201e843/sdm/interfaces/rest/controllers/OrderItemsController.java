package com.blueyonder.platform.u20201e843.sdm.interfaces.rest.controllers;

import com.blueyonder.platform.u20201e843.sdm.domain.services.OrderItemCommandService;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources.CreateOrderItemResource;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.resources.OrderItemResource;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.transform.CreateOrderItemCommandFromResourceAssembler;
import com.blueyonder.platform.u20201e843.sdm.interfaces.rest.transform.OrderItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller exposing endpoints for managing Order Items.
 * <p>
 * Handles HTTP requests for creating new order items
 * within the context of an existing order.
 * </p>
 *
 * @author Author
 */
@RestController
@RequestMapping(value = "/api/v1/orders/{orderId}/items", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Order Items", description = "Endpoints for managing order items")
public class OrderItemsController {
    private final OrderItemCommandService orderItemCommandService;
    /**
     * Constructs the OrderItemsController with the required service.
     *
     * @param orderItemCommandService Application service for handling OrderItem creation commands.
     */
    public OrderItemsController(OrderItemCommandService orderItemCommandService) {
        this.orderItemCommandService = orderItemCommandService;
    }

    /**
     * Handles HTTP POST requests to create a new OrderItem.
     * <p>
     * Transforms the incoming REST resource into a domain command,
     * processes it through the service layer, and returns the created item
     * as a REST resource with HTTP 201 status.
     * </p>
     *
     * @param orderId  The ID of the order this item belongs to (path variable).
     * @param resource The request body containing SKU identifier, requested quantity, and ordered date.
     * @return A ResponseEntity containing the created OrderItemResource and HTTP 201 status,
     *         or HTTP 400 if the request is invalid.
     */
    @PostMapping
    @Operation(summary = "Create an Order Item", description = "Registers a new order item in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order item successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "409", description = "Conflict. Duplicate order item"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<OrderItemResource> createOrderItem(
            @PathVariable Long orderId,
            @RequestBody CreateOrderItemResource resource
    ) {
        var createOrderItemCommand = CreateOrderItemCommandFromResourceAssembler.toCommandFromResource(orderId, resource);
        var orderItem = orderItemCommandService.handle(createOrderItemCommand);
        if (orderItem.isEmpty()) return ResponseEntity.badRequest().build();
        var orderItemEntity = orderItem.get();
        var orderItemResource = OrderItemResourceFromEntityAssembler.toResourceFromEntity(orderItemEntity);
        return new ResponseEntity<>(orderItemResource, HttpStatus.CREATED);
    }
}
