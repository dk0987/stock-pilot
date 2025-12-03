package com.sp.productservice.mapper;

import com.sp.productservice.dto.ProductRequestDTO;
import com.sp.productservice.dto.ProductResponseDTO;
import com.sp.productservice.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static Product toProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        return  product;
    }

    public static ProductResponseDTO toProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStock(product.getStock());
        productResponseDTO.setUnitPrice(product.getUnitPrice());
        productResponseDTO.setCategory(product.getCategory());
        productResponseDTO.setWeight(product.getWeight());
        productResponseDTO.setWeightUnit(product.getWeightUnit());
        productResponseDTO.setDimension(product.getDimension());
        productResponseDTO.setImageURL(product.getImageURL());
        productResponseDTO.setActive(product.isActive());
        productResponseDTO.setCreatedBy(product.getCreatedBy());
        return productResponseDTO;
    }

}
