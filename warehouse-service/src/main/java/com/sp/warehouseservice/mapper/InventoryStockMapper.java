package com.sp.warehouseservice.mapper;

import com.sp.warehouse.GetProductsResponse;
import com.sp.warehouse.ProductDetail;
import com.sp.warehouseservice.dto.*;
import com.sp.warehouseservice.model.InventoryStock;
import com.sp.warehouseservice.model.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryStockMapper {
    private static final Logger log = LoggerFactory.getLogger(InventoryStockMapper.class);

    public static InventoryStockResponseDTO toInventoryStockResponse(List<InventoryStock> inventoryStock , Warehouse warehouse , GetProductsResponse productDetailsResponse ) {
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
           Optional<ProductDetail> productDetail = productDetailsResponse
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

    public static InventoryStock toInventoryStock(InventoryStockRequestDTO inventoryStockRequestDTO) {
        InventoryStock inventoryStock = new InventoryStock();
        inventoryStock.setProductId(inventoryStockRequestDTO.getProduct());
        inventoryStock.setQuantity(inventoryStockRequestDTO.getQuantity());
        inventoryStock.setWarehouseId(inventoryStockRequestDTO.getWarehouse());
        return inventoryStock;
    }

    public static InventoryStock toInventoryStock(InventoryStockUpdateRequestDTO inventoryStockUpdateRequestDTO) {
       InventoryStock inventoryStock = new InventoryStock();
       inventoryStock.setId(inventoryStockUpdateRequestDTO.getInventoryStockId());
       inventoryStock.setQuantity(inventoryStockUpdateRequestDTO.getQuantity());
       return  inventoryStock;
    }

   public static InventoryStockUpdateResponseDTO toInventoryStockUpdateResponseDTO(InventoryStock inventoryStock) {
        InventoryStockUpdateResponseDTO inventoryStockUpdateResponseDTO = new InventoryStockUpdateResponseDTO();
        inventoryStockUpdateResponseDTO.setInventoryStockId(inventoryStock.getId());
        inventoryStockUpdateResponseDTO.setQuantity(inventoryStock.getQuantity());
        inventoryStockUpdateResponseDTO.setProductId(inventoryStock.getProductId());
        inventoryStockUpdateResponseDTO.setWarehouseId(inventoryStock.getWarehouseId());
        return inventoryStockUpdateResponseDTO;
   }
}
