package com.sp.inventory.service;

import com.sp.inventory.dto.SupplierProductRequestDTO;
import com.sp.inventory.mapper.SupplierProductMapper;
import com.sp.inventory.model.SupplierProduct;
import com.sp.inventory.repository.SupplierProductRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

    public SupplierProductService(SupplierProductRepository supplierProductRepository) {
        this.supplierProductRepository = supplierProductRepository;
    }

    private void createSupplierProduct(SupplierProductRequestDTO requestDTO , Long productId) {

        if (requestDTO == null) {
            return;
        }

        try {

            supplierProductRepository.findSupplierProduct(requestDTO.getSupplierId(), productId)
                    .ifPresent((supplierProduct -> {
                        throw new RuntimeException("Supplier product already exists");
                    }));

            SupplierProduct supplierProduct = SupplierProductMapper.toSupplierProduct(requestDTO, productId);
            supplierProductRepository.save(supplierProduct);

        } catch (RuntimeException e) {

            throw new RuntimeException(e);

        }

    }

}
