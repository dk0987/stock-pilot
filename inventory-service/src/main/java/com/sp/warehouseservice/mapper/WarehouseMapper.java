package com.sp.warehouseservice.mapper;

import com.sp.warehouseservice.dto.WarehouseRequestDTO;
import com.sp.warehouseservice.dto.WarehouseResponseDTO;
import com.sp.warehouseservice.model.Warehouse;

import java.time.ZoneOffset;

public class WarehouseMapper {

    public static Warehouse toWarehouse(WarehouseRequestDTO warehouseRequestDTO){
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseRequestDTO.getWarehouseId());
        warehouse.setName(warehouseRequestDTO.getWarehouseName());
        warehouse.setAddress(warehouseRequestDTO.getWarehouseAddress());
        warehouse.setCity(warehouseRequestDTO.getWarehouseCity());
        warehouse.setState(warehouseRequestDTO.getWarehouseState());
        warehouse.setZip(warehouseRequestDTO.getWarehouseZip());
        warehouse.setCountry(warehouseRequestDTO.getWarehouseCountry());
        warehouse.setPhone(warehouseRequestDTO.getWarehousePhone());
        warehouse.setEmail(warehouseRequestDTO.getWarehouseEmail());
        warehouse.setUserId(warehouseRequestDTO.getManagedBy());
        return warehouse;
    }

    public static WarehouseResponseDTO toWarehouseResponseDTO(Warehouse warehouse){
        WarehouseResponseDTO warehouseResponseDTO = new WarehouseResponseDTO();
        warehouseResponseDTO.setWarehouseId(warehouse.getId());
        warehouseResponseDTO.setWarehouseName(warehouse.getName());
        warehouseResponseDTO.setWarehouseAddress(warehouse.getAddress());
        warehouseResponseDTO.setWarehouseCity(warehouse.getCity());
        warehouseResponseDTO.setWarehouseState(warehouse.getState());
        warehouseResponseDTO.setWarehouseZip(warehouse.getZip());
        warehouseResponseDTO.setWarehouseCountry(warehouse.getCountry());
        warehouseResponseDTO.setWarehousePhone(warehouse.getPhone());
        warehouseResponseDTO.setWarehouseEmail(warehouse.getEmail());
        warehouseResponseDTO.setManagedBy(warehouse.getUserId());
        warehouseResponseDTO.setWarehouseIsActive(warehouse.isActive());
        warehouseResponseDTO.setManagedBy(warehouse.getUserId());
        warehouseResponseDTO.setCreatedBy(warehouse.getCreatedBy());
        warehouseResponseDTO.setCreatedAt(warehouse.getCreatedAt().atOffset(ZoneOffset.UTC));
        return warehouseResponseDTO;
    }
}
