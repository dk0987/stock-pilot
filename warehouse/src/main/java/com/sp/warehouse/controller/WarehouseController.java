package com.sp.warehouse.controller;

import com.sp.warehouse.dto.WarehouseRequestDTO;
import com.sp.warehouse.dto.WarehouseResponseResponseDTO;
import com.sp.warehouse.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<WarehouseResponseResponseDTO> createWarehouse(
            @Valid @RequestBody         WarehouseRequestDTO request,
            @RequestHeader("X-User-Id") Long createdBy
    ){
        WarehouseResponseResponseDTO warehouseResponseDTO = warehouseService.createWarehouse(request,createdBy);

        if(warehouseResponseDTO != null) {
            return ResponseEntity.ok(warehouseResponseDTO);
        } else  {
            return ResponseEntity.badRequest().build();
        }

    }

}
