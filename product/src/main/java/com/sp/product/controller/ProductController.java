package com.sp.product.controller;

import com.sp.product.dto.ProductRequestDTO;
import com.sp.product.dto.ProductResponseDTO;
import com.sp.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestBody ProductRequestDTO request,
            @RequestHeader("X-User-Id") Long createdBy
    ){
        ProductResponseDTO productResponseDTO = productService.createProduct(request,createdBy);
        log.info("Received Add Product Request - {}", productResponseDTO);
        if(productResponseDTO != null) {
            return ResponseEntity.ok(productResponseDTO);
        } else  {
            return ResponseEntity.badRequest().build();
        }

    }

}
