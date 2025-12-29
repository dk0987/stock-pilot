package com.sp.product.event;

import com.sp.product.dto.ProductRequestDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductCreatedInternalEvent {

    private final Long productId;
    private final ProductRequestDTO productRequest;

    public ProductCreatedInternalEvent(
            Long productId,
            ProductRequestDTO productRequest
    ) {
        this.productId = productId;
        this.productRequest = productRequest;
    }

}
