package com.sp.warehouse.mapper;


import com.sp.warehouse.dto.AddressResponseDTO;
import com.sp.warehouse.dto.WarehouseRequestDTO;
import com.sp.warehouse.dto.WarehouseResponseDTO;
import com.sp.warehouse.model.Warehouse;

public class WarehouseMapper {

    public static Warehouse toWarehouse(WarehouseRequestDTO request , Long addressId){

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setWarehouseCode(request.getWarehouseCode());
        warehouse.setMaxCapacity(request.getMaxCapacity());
        warehouse.setAddressId(addressId);
        return warehouse;

    }

    public static WarehouseResponseDTO toWarehouseResponseDTO(
            Warehouse warehouse,
            AddressResponseDTO address
    ){

        WarehouseResponseDTO warehouseResponseDTO = new WarehouseResponseDTO();
        warehouseResponseDTO.setId(warehouse.getId());
        warehouseResponseDTO.setWarehouseName(warehouse.getWarehouseName());
        warehouseResponseDTO.setWarehouseCode(warehouse.getWarehouseCode());
        warehouseResponseDTO.setMaxCapacity(warehouse.getMaxCapacity());
        warehouseResponseDTO.setAddress(address);
        warehouseResponseDTO.setCreatedAt(warehouse.getCreatedAt());
        warehouseResponseDTO.setUpdatedAt(warehouse.getUpdatedAt());
        warehouseResponseDTO.setUpdatedBy(warehouse.getUpdatedBy());
        warehouseResponseDTO.setCreatedBy(warehouse.getCreatedBy());
        warehouseResponseDTO.setActive(warehouse.isActive());
        return warehouseResponseDTO;

    }

}
