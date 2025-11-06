package com.sp.warehouseservice.service;

import com.sp.warehouse.GetProductsResponse;
import com.sp.warehouseservice.dto.InventoryStockRequestDTO;
import com.sp.warehouseservice.dto.InventoryStockResponseDTO;
import com.sp.warehouseservice.dto.InventoryStockUpdateRequestDTO;
import com.sp.warehouseservice.dto.InventoryStockUpdateResponseDTO;
import com.sp.warehouseservice.grpc.ProductServiceClient;
import com.sp.warehouseservice.mapper.InventoryStockMapper;
import com.sp.warehouseservice.model.InventoryStock;
import com.sp.warehouseservice.model.Warehouse;
import com.sp.warehouseservice.repository.InventoryStockRepository;
import com.sp.warehouseservice.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryStockService {
    private static final Logger log = LoggerFactory.getLogger(InventoryStockService.class);
    private final InventoryStockRepository inventoryStockRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductServiceClient productServiceClient;

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
                GetProductsResponse productDetails = productServiceClient.getProductDetailsResponse(
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

    public InventoryStockResponseDTO createInventoryStock(InventoryStockRequestDTO inventoryStockRequestDTO) {
        try{
            // Return if the request is null
            if(inventoryStockRequestDTO == null ){
                throw new RuntimeException("inventory stock request DTO is null");
            }

            List<String> productId = new ArrayList<>();                   // productId list
            List<InventoryStock> inventoryStockList = new ArrayList<>(); // inventory stock list

            // Add product id in a list to pass it to the mapper
            productId.add(inventoryStockRequestDTO.getProduct().toString());
            GetProductsResponse productDetails = productServiceClient.getProductDetailsResponse(productId);

            // Fetch related warehouse and return if warehouse doesn't exist
            Warehouse warehouse = warehouseRepository.findById(inventoryStockRequestDTO.getWarehouse())
                    .orElseThrow(() -> new RuntimeException("warehouse not found"));

            // Return if the product does not exist
            if (productDetails == null) {
                throw new RuntimeException("product details not found");
            }

            // Throwing exceptions if the quantity is in negative
            if (inventoryStockRequestDTO.getQuantity() < 0){
                throw new RuntimeException("quantity is negative");
            }

            // Updating the quantity only (don't want to delete the inventory of certain product from an inventory)
            // for deleting we can update it to 0
            if (productDetails.getProductsList().get(0).getProductMaxOccupancy() < inventoryStockRequestDTO.getQuantity()) {
                throw new RuntimeException("product max occupancy not enforced");
            }

            // Map the request to model inventory stock
            InventoryStock inventoryStock = InventoryStockMapper.toInventoryStock(inventoryStockRequestDTO);
            inventoryStock.setCreatedAt(LocalDateTime.now());
            inventoryStock.setCreatedBy(UUID.fromString("a0e9b8c7-d6f5-e4d3-c2b1-100000000008"));

            // Save the inventory in Inventory Stock db and
            // add the returned inventory stock into list to map to inventory response
            inventoryStockList.add(inventoryStockRepository.save(inventoryStock));

            return InventoryStockMapper.toInventoryStockResponse( inventoryStockList , warehouse , productDetails);

        }catch (RuntimeException e){
            log.info("Failed to create inventory stock");
            throw new RuntimeException("Failed to create inventory stock" + e.getMessage());
        }
    }

    public InventoryStockUpdateResponseDTO updateInventoryStock(InventoryStockUpdateRequestDTO request) {
        try{
            if (request == null ){
                throw new RuntimeException("inventory stock request DTO is null");
            }

            if (request.getInventoryStockId() == null){
                throw new RuntimeException("inventory stock id is null");
            }

            List<String> productId = new ArrayList<>(); // Product list

            // Finding if the inventory stock does exist or not
            InventoryStock inventoryStock =  inventoryStockRepository.findById(request.getInventoryStockId())
                    .orElseThrow(() -> new RuntimeException("inventory stock not found"));

            // Adding to product list to get the product detail
            productId.add(inventoryStock.getProductId().toString());
            GetProductsResponse productDetails = productServiceClient.getProductDetailsResponse(productId);

            // Throwing exceptions if the quantity is in negative
            if (request.getQuantity() < 0){
                throw new RuntimeException("quantity is negative");
            }

            // Checking if the max occupancy of the product is reached or not
            if (productDetails.getProductsList().get(0).getProductMaxOccupancy() < request.getQuantity() + inventoryStock.getQuantity()){
                throw new RuntimeException("product max occupancy not enforced");
            }

            // Updating the quantity only (don't want to delete the inventory of certain product from an inventory)
            // for deleting we can update it to 0
            inventoryStock.setQuantity(request.getQuantity());
            InventoryStock updatedInventoryStock = inventoryStockRepository.save(inventoryStock);

            return InventoryStockMapper.toInventoryStockUpdateResponseDTO(updatedInventoryStock);

        }catch (RuntimeException e){
            log.info("Failed to update inventory stock");
            throw new RuntimeException("Failed to update inventory stock" + e.getMessage());
        }
    }
}

