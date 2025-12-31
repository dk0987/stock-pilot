package com.sp.inventory.service;

import com.example.inventoryProto.ProductsEvents;
import com.sp.inventory.dto.StockThresholdRequestDTO;
import com.sp.inventory.dto.SupplierProductRequestDTO;
import com.sp.inventory.grpc.PartnersServiceClient;
import com.sp.inventory.kafka.producer.InventoryResultProducer;
import com.sp.inventory.mapper.StockThresholdMapper;
import com.sp.inventory.mapper.SupplierProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryProvisionService {

    private final StockThresholdService   stockThresholdService;
    private final SupplierProductService  supplierProductService;
    private final InventoryResultProducer inventoryResultProducer;


    public InventoryProvisionService(
            StockThresholdService stockThresholdService ,
            SupplierProductService supplierProductService,
            InventoryResultProducer inventoryResultProducer
    ) {
        this.stockThresholdService = stockThresholdService;
        this.supplierProductService = supplierProductService;
        this.inventoryResultProducer = inventoryResultProducer;
    }

    @Transactional
    public void provision( ProductsEvents.ProductCreatedEvent parsedEvent) {

        Long productId = parsedEvent.getProductId();

        Set<StockThresholdRequestDTO> stockThresholdsRequest = parsedEvent.getStockThresholdsDetailsList()
                .stream()
                .map(StockThresholdMapper::toStockThresholdRequest)
                .collect(Collectors.toSet());

        SupplierProductRequestDTO supplierProductRequest = SupplierProductMapper
                .toSupplierProductRequest(parsedEvent.getSupplierProductDetails());

        try{

            supplierProductService.createSupplierProduct(productId , supplierProductRequest);
            stockThresholdService.createStockThreshold(productId , stockThresholdsRequest);

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    inventoryResultProducer.sendStockThresholdSuccessful(productId);
                }
            });

        } catch (RuntimeException e){

            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {
                        @Override
                        public void afterCompletion(int status) {
                            if (status == STATUS_ROLLED_BACK) {
                                inventoryResultProducer.sendStockThresholdFailed(productId , "Something went wrong");
                            }
                        }
                    }
            );
            throw e;

        }



    }


}
