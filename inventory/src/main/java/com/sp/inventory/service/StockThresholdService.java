package com.sp.inventory.service;

import com.sp.inventory.dto.StockThresholdRequestDTO;
import com.sp.inventory.dto.WarehouseGRPCResponseDTO;
import com.sp.inventory.grpc.WarehouseServiceClient;
import com.sp.inventory.mapper.StockThresholdMapper;
import com.sp.inventory.model.StockThreshold;
import com.sp.inventory.repository.StockThresholdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockThresholdService {

    private final StockThresholdRepository stockThresholdRepository;
    private final WarehouseServiceClient   warehouseServiceClient;

    public StockThresholdService(
            StockThresholdRepository stockThresholdRepository,
            WarehouseServiceClient warehouseServiceClient
    ) {
        this.stockThresholdRepository = stockThresholdRepository;
        this.warehouseServiceClient = warehouseServiceClient;
    }

    @Transactional
    public void createStockThreshold(
            Long productId,
            Set<StockThresholdRequestDTO> requests
    ) {

        if (requests == null || requests.isEmpty()) {
            throw new IllegalArgumentException("Stock threshold request is empty");
        }

        // 1. Collect warehouse IDs
        Set<Long> warehouseIds = requests.stream()
                .map(StockThresholdRequestDTO::getWarehouseId)
                .collect(Collectors.toSet());

        // 2. Fetch warehouses via gRPC
        Set<WarehouseGRPCResponseDTO> existingWarehouses =
                warehouseServiceClient.getExistingWarehouses(warehouseIds);

        if (existingWarehouses.isEmpty()) {
            throw new RuntimeException("Warehouse not found");
        }

        // 3. Convert to Map for O(1) lookup
        Map<Long, WarehouseGRPCResponseDTO> warehouseMap =
                existingWarehouses.stream()
                        .collect(Collectors.toMap(
                                WarehouseGRPCResponseDTO::getWarehouseID,
                                Function.identity()
                        ));

        // 4. Fetch all thresholds once
        List<StockThreshold> stockByWarehouse =
                stockThresholdRepository.findThresholdByWarehouseId(
                        new ArrayList<>(warehouseIds)
                );

        // 5. Group by warehouseId
        Map<Long, List<StockThreshold>> thresholdByWarehouse =
                stockByWarehouse.stream()
                        .collect(Collectors.groupingBy(StockThreshold::getWarehouseId));

        // 6. Process requests
        for (StockThresholdRequestDTO request : requests) {

            // Duplicate check
            stockThresholdRepository.findThreshold(productId, request.getWarehouseId())
                    .ifPresent(t -> {
                        throw new RuntimeException("Threshold already exists");
                    });

            WarehouseGRPCResponseDTO warehouse =
                    warehouseMap.get(request.getWarehouseId());

            if (warehouse == null) {
                throw new RuntimeException("Warehouse not found");
            }

            int alreadyStockedAmount =
                    thresholdByWarehouse
                            .getOrDefault(request.getWarehouseId(), List.of())
                            .stream()
                            .mapToInt(StockThreshold::getMaxQuantity)
                            .sum();

            int availableCapacity =
                    warehouse.getMaxQuantity() - alreadyStockedAmount;

            if (availableCapacity < request.getMaxQuantity()) {
                throw new RuntimeException("Capacity exceeded");
            }

            StockThreshold stockThreshold =
                    StockThresholdMapper.toStockThreshold(request, productId);

            stockThresholdRepository.save(stockThreshold);
        }
    }


}
