package com.noser.vendingmachine.controller;

import com.noser.vendingmachine.model.InventoryItem;
import com.noser.vendingmachine.model.Product;
import com.noser.vendingmachine.model.VendingMachine;
import com.noser.vendingmachine.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    //TODO: CRUD operations for the products (add, update, remove)

    @GetMapping(path = "/{machineId}/inventory", produces = "application/json")
    public List<InventoryItem> getInventoryForMachine(@PathVariable("machineId") String machineId) {
        return inventoryService.getInventoryForMachine(machineId);
    }

    @PostMapping("/{machineId}/inventory")
    public void addProductToMachine(@PathVariable("machineId") String machineId, @RequestBody InventoryItem inventoryItem) {
        inventoryService.addProductToMachine(machineId, inventoryItem);
    }

    @PostMapping("/products")
    @Operation(summary = "Create product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)})
    public Product createProduct(@RequestBody Product product) {
        return inventoryService.createProduct(product);
    }

    @DeleteMapping("/{machineId}/inventory")
    public void removeProductFromMachine(@PathVariable("machineId") String machineId, @RequestBody InventoryItem inventoryItem) {
        inventoryService.removeProductFromMachine(machineId, inventoryItem);
    }

    @PostMapping(path = "/machines", produces = "application/json")
    @Operation(summary = "Create machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Machine created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendingMachine.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)})
    public VendingMachine createMachine(@RequestBody VendingMachine vendingMachine) {
        return inventoryService.createMachine(vendingMachine);
    }

}
