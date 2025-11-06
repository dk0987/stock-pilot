package com.sp.productservice.service;

import com.sp.productservice.dto.ProductResponseDTO;
import com.sp.productservice.mapper.ProductMapper;
import com.sp.productservice.model.Product;
import com.sp.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::toProductResponseDTO).toList();
    }

    public List<Product> getProductDetails(List<UUID> productIds) {
        log.info("getProductDetails request received{}",productIds);
        log.info("getProductDetails request received{}", productRepository.findAllById(productIds));
        log.info("getProductDetails request received2{}", productRepository.findAll());
        return productRepository.findAllById(productIds);
    }

}
