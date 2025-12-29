package com.sp.inventory.service;

import com.sp.inventory.dto.SupplierProductRequestDTO;
import com.sp.inventory.kafka.producer.InventoryResultProducer;
import com.sp.inventory.mapper.SupplierProductMapper;
import com.sp.inventory.model.SupplierProduct;
import com.sp.inventory.repository.SupplierProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

    public SupplierProductService(
            SupplierProductRepository supplierProductRepository,
            InventoryResultProducer inventoryResultProducer
    ) {
        this.supplierProductRepository = supplierProductRepository;
    }

    @Transactional
    public void createSupplierProduct(Long productId , SupplierProductRequestDTO requestDTO ) {

        if (requestDTO == null) {
            throw new RuntimeException("Supplier product request is null");
        }

        try {

            supplierProductRepository.findSupplierProduct(requestDTO.getSupplierId(), productId)
                    .ifPresent(supplierProduct -> {throw new RuntimeException("Supplier product not found");});

            SupplierProduct supplierProduct = SupplierProductMapper.toSupplierProduct(requestDTO, productId);
            supplierProductRepository.save(supplierProduct);



        } catch (RuntimeException e) {

            throw new RuntimeException(e.getMessage());

        }

    }

}
