package com.sp.productservice.service;

import com.sp.productservice.dto.ProductResponseDTO;
import com.sp.productservice.mapper.ProductMapper;
import com.sp.productservice.model.Product;
import com.sp.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::toProductResponseDTO).toList();
    }


}
