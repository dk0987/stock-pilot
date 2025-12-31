package com.sp.inventory.mapper;

import com.example.grpcProto.Warehouse;
import com.example.grpcProto.WarehouseGRPCResponse;
import com.sp.inventory.dto.WarehouseGRPCResponseDTO;

public class WarehouseMapper {

    public static WarehouseGRPCResponseDTO toWarehouseGRPCResponseDTO(Warehouse warehouse) {

        WarehouseGRPCResponseDTO dto = new WarehouseGRPCResponseDTO();
        dto.setWarehouseID(warehouse.getWarehouseId());
        dto.setMaxQuantity(warehouse.getMaxQuantity());

        return dto;

    }

}
