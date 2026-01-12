package com.sp.product.event;

import com.sp.product.dto.ProductRequestDTO;

public record ProductCreatedInternalEvent(Long productId, ProductRequestDTO productRequest) {

}
