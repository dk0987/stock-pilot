package com.sp.warehouseservice.controller;

import com.sp.warehouseservice.dto.InventoryStockRequestDTO;
import com.sp.warehouseservice.dto.InventoryStockResponseDTO;
import com.sp.warehouseservice.dto.InventoryStockUpdateRequestDTO;
import com.sp.warehouseservice.dto.InventoryStockUpdateResponseDTO;
import com.sp.warehouseservice.service.InventoryStockService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class InventoryStockController {

    private final InventoryStockService inventoryStockService;

    public InventoryStockController(InventoryStockService inventoryStockService) {
        this.inventoryStockService = inventoryStockService;
    }

    @QueryMapping
    public InventoryStockResponseDTO findInventoryByWarehouseId(@Argument("warehouseId") UUID warehouseId) {
        return inventoryStockService.getInventoryStockByWarehouseId(warehouseId);
    }

    @MutationMapping
    public InventoryStockResponseDTO createInventoryStock(@Argument("input") InventoryStockRequestDTO inventoryStockRequestDTO) {
        return inventoryStockService.createInventoryStock(inventoryStockRequestDTO);
    }

    @MutationMapping
    public InventoryStockUpdateResponseDTO updateInventoryStockQuantity(@Argument("input") InventoryStockUpdateRequestDTO inventoryStockUpdateRequestDTO) {
        return inventoryStockService.updateInventoryStock(inventoryStockUpdateRequestDTO);
    }
}
