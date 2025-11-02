package com.sp.warehouseservice.service;

import com.sp.warehouseservice.dto.InventoryStockResponseDTO;
import com.sp.warehouseservice.grpc.ProductServiceClient;
import com.sp.warehouseservice.mapper.InventoryStockMapper;
import com.sp.warehouseservice.model.InventoryStock;
import com.sp.warehouseservice.model.Warehouse;
import com.sp.warehouseservice.repository.InventoryStockRepository;
import com.sp.warehouseservice.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryStockService {
    private static final Logger log = LoggerFactory.getLogger(InventoryStockService.class);
    private final InventoryStockRepository inventoryStockRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductServiceClient productServiceClient;
            ;
    public InventoryStockService(
            InventoryStockRepository inventoryStockRepository ,
            WarehouseRepository warehouseRepository ,
            ProductServiceClient productServiceClient
    ) {
        this.inventoryStockRepository = inventoryStockRepository;
        this.warehouseRepository = warehouseRepository;
        this.productServiceClient = productServiceClient;
    }

    public InventoryStockResponseDTO getInventoryStockByWarehouseId(UUID warehouseId) {
        try{
            boolean doesWarehouseExists = warehouseRepository.existsById(warehouseId);
            if (!doesWarehouseExists){
                throw new RuntimeException("Warehouse does not exist");
            }
            boolean ifExists = inventoryStockRepository.existsByWarehouseId(warehouseId);
            if(!ifExists){
                throw new RuntimeException("Warehouse does not exist in inventory list");
            } else {
                List<InventoryStock> inventoryAccordingWarehouse = inventoryStockRepository.getByWarehouseId(warehouseId);
                Warehouse warehouseDetail = warehouseRepository.findById(warehouseId)
                        .orElseThrow(() -> new RuntimeException("warehouse not found"));
                com.sp.warehouse.GetProductsResponse productDetails = productServiceClient.getProductDetailsResponse(
                        inventoryAccordingWarehouse.stream()
                                .map(i-> i.getProductId().toString())
                                .toList());
                InventoryStockResponseDTO inventoryStockResponseDTO ;
                inventoryStockResponseDTO = InventoryStockMapper.toInventoryStockResponse(inventoryAccordingWarehouse , warehouseDetail , productDetails);
                return inventoryStockResponseDTO;
            }
        } catch (RuntimeException e){
            log.error(e.getMessage());
            throw new RuntimeException("Failed to get inventory stock" + e.getMessage());
        }
    }
}

