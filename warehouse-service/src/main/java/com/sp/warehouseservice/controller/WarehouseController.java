package com.sp.warehouseservice.controller;

import com.sp.warehouseservice.dto.WarehouseRequestDTO;
import com.sp.warehouseservice.dto.WarehouseResponseDTO;
import com.sp.warehouseservice.service.WarehouseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @MutationMapping
    public WarehouseResponseDTO warehouseCreate(@Argument("input") WarehouseRequestDTO warehouseRequestDTO) {
        return warehouseService.createWarehouse(warehouseRequestDTO);
    }

    @MutationMapping
    public WarehouseResponseDTO warehouseUpdate(@Argument("input") WarehouseRequestDTO warehouseRequestDTO) {
        return warehouseService.updateWarehouseById(warehouseRequestDTO);
    }

    @MutationMapping
    public boolean warehouseDelete(@Argument("warehouseId") UUID warehouseId) {
        return warehouseService.deleteWarehouseById(warehouseId);
    }


}
