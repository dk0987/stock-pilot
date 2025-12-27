package com.sp.product.mapper;

import com.sp.product.dto.ProductRequestDTO;
import com.sp.product.dto.ProductResponseDTO;
import com.sp.product.model.Product;

public class ProductMapper {

    public static Product toProduct(ProductRequestDTO request){

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSku(request.getSku());
        product.setWeight(request.getWeight());
        product.setDimension(request.getDimension());
        product.setWeightUnit(request.getWeightUnit());
        product.setImageUrl(request.getImageUrl());
        return product;

    }

    public static ProductResponseDTO toProductResponseDTO(Product product){

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setProductId(product.getId());
        productResponseDTO.setProductName(product.getName());
        productResponseDTO.setProductDescription(product.getDescription());
        productResponseDTO.setDimension(product.getDimension());
        productResponseDTO.setWeightUnit(product.getWeightUnit());
        productResponseDTO.setImageUrl(product.getImageUrl());
        productResponseDTO.setCreatedBy(product.getCreatedBy());
        productResponseDTO.setCreatedAt(product.getCreatedAt());
        productResponseDTO.setUpdatedBy(product.getUpdatedBy());
        productResponseDTO.setUpdatedAt(product.getUpdatedAt());
        productResponseDTO.setActive(product.isActive());

        return productResponseDTO;

    }

}
