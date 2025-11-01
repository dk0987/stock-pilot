package com.sp.warehouseservice.controller;

import com.sp.warehouseservice.dto.InventoryStockResponseDTO;
import com.sp.warehouseservice.service.InventoryStockService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class InventoryController {

    private final InventoryStockService inventoryStockService;

    public InventoryController(InventoryStockService inventoryStockService) {
        this.inventoryStockService = inventoryStockService;
    }

    @QueryMapping
    public InventoryStockResponseDTO findInventoryByWarehouse(@Argument("warehouseId") UUID warehouseId) {
        return inventoryStockService.getInventoryStockByWarehouseId(warehouseId);
    }
}
