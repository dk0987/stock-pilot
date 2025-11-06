package com.sp.warehouseservice.mapper;

import com.google.type.DateTime;
import com.sp.warehouseservice.dto.InventoryStockResponseDTO;
import com.sp.warehouseservice.dto.StockDetailsResponseDTO;
import com.sp.warehouseservice.model.InventoryStock;
import com.sp.warehouseservice.model.Warehouse;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryStockMapper {
    public static InventoryStockResponseDTO toInventoryStockResponse(List<InventoryStock> inventoryStock , Warehouse warehouse , com.sp.warehouse.GetProductsResponse productDetailsResponse ) {
        InventoryStockResponseDTO inventoryStockResponseDTO = new InventoryStockResponseDTO();
        List<StockDetailsResponseDTO> stockDetails = new ArrayList<>();
        StockDetailsResponseDTO stockDetail = new StockDetailsResponseDTO();
        inventoryStockResponseDTO.setWarehouse(warehouse.getId());
        inventoryStockResponseDTO.setWarehouseName(warehouse.getName());
        inventoryStockResponseDTO.setWarehouseAddress(warehouse.getAddress());
        inventoryStockResponseDTO.setWarehousePhone(warehouse.getPhone());
        inventoryStockResponseDTO.setWarehouseCity(warehouse.getCity());
        inventoryStockResponseDTO.setWarehouseZip(warehouse.getZip());
        inventoryStockResponseDTO.setWarehouseCountry(warehouse.getCountry());
        inventoryStockResponseDTO.setWarehouseStates(warehouse.getState());
        inventoryStockResponseDTO.setWarehousEmail(warehouse.getEmail());
        inventoryStockResponseDTO.setWarehouseCreatedAt(warehouse.getCreatedAt().atOffset(ZoneOffset.UTC));
        inventoryStockResponseDTO.setWarehouseCreatedBy(warehouse.getCreatedBy());
        inventoryStock.forEach(item -> {
           Optional<com.sp.warehouse.ProductDetail> productDetail = productDetailsResponse
                   .getProductsList()
                   .stream()
                   .filter(p-> item.getProductId().toString().equals(p.getProductId()))
                   .findFirst();
           stockDetail.setStockId(item.getId());
           stockDetail.setProductQuantity(item.getQuantity());
           if (productDetail.isPresent()) {
               stockDetail.setProductName(productDetail.get().getProductName());
               stockDetail.setProductDescription(productDetail.get().getProductDescription());
               stockDetail.setProductCategory(productDetail.get().getProductCategory());
               stockDetail.setProductId(item.getProductId());
               stockDetail.setProductMaxOccupancy(productDetail.get().getProductMaxOccupancy());
           }
           stockDetails.add(stockDetail);
           inventoryStockResponseDTO.setStockDetails(stockDetails);
        });
        return inventoryStockResponseDTO;
    }
}
