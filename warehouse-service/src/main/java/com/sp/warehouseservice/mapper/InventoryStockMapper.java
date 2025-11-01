package com.sp.warehouseservice.mapper;

import com.sp.warehouseservice.dto.InventoryStockResponseDTO;
import com.sp.warehouseservice.model.InventoryStock;
import com.sp.warehouseservice.model.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryStockMapper {
    public static InventoryStockResponseDTO toInventoryStockResponse(List<InventoryStock> inventoryStock , Warehouse warehouse ) {
        InventoryStockResponseDTO inventoryStockResponseDTO = new InventoryStockResponseDTO();
        List<UUID> inventoryDetail = new ArrayList<>();
        inventoryStockResponseDTO.setWarehouse(warehouse.getId());
        inventoryStockResponseDTO.setWarehouseName(warehouse.getName());
        inventoryStockResponseDTO.setWarehouseAddress(warehouse.getAddress());
        inventoryStockResponseDTO.setWarehousePhone(warehouse.getPhone());
        inventoryStockResponseDTO.setWarehouseCity(warehouse.getCity());
        inventoryStockResponseDTO.setWarehouseZip(warehouse.getZip());
        inventoryStockResponseDTO.setWarehouseCountry(warehouse.getCountry());
        inventoryStockResponseDTO.setWarehouseStates(warehouse.getState());
        inventoryStockResponseDTO.setWarehousEmail(warehouse.getEmail());
        inventoryStockResponseDTO.setWarehouseCreatedAt(warehouse.getCreatedAt());
        inventoryStockResponseDTO.setWarehouseCreatedBy(warehouse.getCreatedBy());
        inventoryStock.forEach(item -> {
            inventoryDetail.add(item.getId());
        });
        inventoryStockResponseDTO.setId(inventoryDetail);
        return inventoryStockResponseDTO;
    }
}
