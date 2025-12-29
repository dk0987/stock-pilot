package com.sp.product.service;

import com.example.productProto.ProductsEvents;
import com.sp.product.dto.ProductRequestDTO;
import com.sp.product.dto.ProductResponseDTO;
import com.sp.product.event.ProductCreatedInternalEvent;
import com.sp.product.kafka.producer.ProductProducer;
import com.sp.product.mapper.ProductMapper;
import com.sp.product.model.Product;
import com.sp.product.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProductService(
            ProductRepository productRepository,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProductResponseDTO createProduct(ProductRequestDTO request , Long createdBy){

        if (request == null) {
            throw new RuntimeException("Product request is null");
        }

        productRepository.findBySku(request.getSku())
                .ifPresent(product -> {throw new RuntimeException("Product already exists");});

        Product product = ProductMapper.toProduct(request);
        product.setCreatedBy(createdBy);

        Product createdProduct = productRepository.save(product);

        applicationEventPublisher.publishEvent(
                new ProductCreatedInternalEvent(createdProduct.getId() , request)
        );

       return ProductMapper.toProductResponseDTO(createdProduct);

    }

    @Transactional
    public void deleteProduct(Long id){
        if (id == null) {
            throw new RuntimeException("Product id is null");
        }

        productRepository.findById(id.intValue())
                        .orElseThrow(() -> new RuntimeException("Product not exists"));

        productRepository.deleteById(id.intValue());
    }

}
