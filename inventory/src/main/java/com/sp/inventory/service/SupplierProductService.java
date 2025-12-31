package com.sp.inventory.service;

import com.sp.inventory.dto.SupplierProductRequestDTO;
import com.sp.inventory.grpc.PartnersServiceClient;
import com.sp.inventory.kafka.producer.InventoryResultProducer;
import com.sp.inventory.mapper.SupplierProductMapper;
import com.sp.inventory.model.SupplierProduct;
import com.sp.inventory.repository.SupplierProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;
    private final PartnersServiceClient     partnersServiceClient;

    public SupplierProductService(
            SupplierProductRepository supplierProductRepository,
            PartnersServiceClient partnersServiceClient
    ) {
        this.supplierProductRepository = supplierProductRepository;
        this.partnersServiceClient = partnersServiceClient;
    }

    @Transactional
    public void createSupplierProduct(Long productId , SupplierProductRequestDTO requestDTO ) {

        if (requestDTO == null) {
            throw new RuntimeException("Supplier product request is null");
        }

        try {

            boolean supplierExist = partnersServiceClient.doesPartnerExist(requestDTO.getSupplierId());

            if (!supplierExist) {
                throw new RuntimeException("Supplier does not exist");
            }

            supplierProductRepository.findSupplierProduct(requestDTO.getSupplierId(), productId)
                    .ifPresent(supplierProduct -> {throw new RuntimeException("Supplier product already exists");});


            SupplierProduct supplierProduct = SupplierProductMapper.toSupplierProduct(requestDTO, productId);
            supplierProductRepository.save(supplierProduct);


        } catch (RuntimeException e) {

            throw new RuntimeException(e.getMessage());

        }

    }

}
