package com.sp.inventory.service;

import com.sp.inventory.dto.StockThresholdRequestDTO;
import com.sp.inventory.mapper.StockThresholdMapper;
import com.sp.inventory.model.StockThreshold;
import com.sp.inventory.repository.StockThresholdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
public class StockThresholdService {

    private final StockThresholdRepository stockThresholdRepository;

    public StockThresholdService(
            StockThresholdRepository stockThresholdRepository
    ) {
        this.stockThresholdRepository = stockThresholdRepository;
    }

    @Transactional
    public void createStockThreshold(
            Long productId ,
            Set<StockThresholdRequestDTO> requests
    ) {

        if (requests == null || requests.isEmpty()) {
            throw new RuntimeException("Stock Threshold Request is empty");
        }

        try {

            requests.forEach(request -> {

                stockThresholdRepository.findThreshold(productId, request.getWarehouseId())
                        .ifPresent(threshold -> {
                            throw new RuntimeException("Threshold already exists");
                        });

                StockThreshold stockThreshold = StockThresholdMapper.toStockThreshold(request, productId);
                stockThresholdRepository.save(stockThreshold);

            });

        } catch (RuntimeException e){
            log.info("Failed to create stock threshold ${}" ,e.getMessage());
            throw e;
        }


    }

}
