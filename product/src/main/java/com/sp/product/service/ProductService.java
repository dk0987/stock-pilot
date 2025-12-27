package com.sp.product.service;

import com.sp.product.dto.ProductRequestDTO;
import com.sp.product.dto.ProductResponseDTO;
import com.sp.product.dto.SupplierProductRequestDTO;
import com.sp.product.dto.WarehouseRequestDTO;
import com.sp.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO request , Long createdBy){

        if (request == null) {
            throw new RuntimeException("Product request is null");
        }

        productRepository.findBySku(request.getSku())
                .ifPresent(product -> {throw new RuntimeException("Product already exists");});

       createWarehousesForProduct(request.getWarehouseIds());
       createSupplierForProduct(request.getSupplierProductDetail());
       return null;

    }

    private void createWarehousesForProduct(Set<WarehouseRequestDTO> warehouses){

    }

    private void createSupplierForProduct(SupplierProductRequestDTO supplierProductDetail){

    }

}
