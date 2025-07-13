package com.blueyonder.platform.u20201e843.scm.interfaces.rest.controllers;

import com.blueyonder.platform.u20201e843.scm.domain.services.InventoryItemCommandService;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources.CreateInventoryItemResource;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.resources.InventoryItemResource;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.transform.CreateInventoryItemCommandFromResourceAssembler;
import com.blueyonder.platform.u20201e843.scm.interfaces.rest.transform.InventoryItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/inventory-items", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Inventory Items", description = "Endpoints for managing inventory items")
public class InventoryItemsController {
    private final InventoryItemCommandService inventoryItemCommandService;

    public InventoryItemsController(InventoryItemCommandService inventoryItemCommandService) {
        this.inventoryItemCommandService = inventoryItemCommandService;
    }

    @PostMapping
    @Operation(summary = "Create an Inventory Item", description = "Registers a new inventory item in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventory item successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "409", description = "Inventory item with given SKU already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<InventoryItemResource> createInventoryItem(@RequestBody CreateInventoryItemResource resource) {
        var createInventoryItemCommand = CreateInventoryItemCommandFromResourceAssembler.toCommandFromResource(resource);
        var inventoryItem = inventoryItemCommandService.handle(createInventoryItemCommand);
        if (inventoryItem.isEmpty()) return ResponseEntity.badRequest().build();
        var inventoryItemEntity = inventoryItem.get();
        var inventoryItemResource = InventoryItemResourceFromEntityAssembler.toResourceFromEntity(inventoryItemEntity);
        return new ResponseEntity<>(inventoryItemResource, HttpStatus.CREATED);
    }
}
