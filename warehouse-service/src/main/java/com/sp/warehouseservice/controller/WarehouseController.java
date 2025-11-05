package com.sp.warehouseservice.controller;

import com.sp.warehouseservice.dto.InventoryStockResponseDTO;
import com.sp.warehouseservice.dto.WarehouseResponseDTO;
import com.sp.warehouseservice.service.InventoryStockService;
import com.sp.warehouseservice.service.WarehouseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @QueryMapping
    public List<WarehouseResponseDTO> findAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @QueryMapping
    public WarehouseResponseDTO findWarehouseById(@Argument("warehouseId") UUID warehouseId) {
        return warehouseService.getWarehouseById(warehouseId);
    }


}
