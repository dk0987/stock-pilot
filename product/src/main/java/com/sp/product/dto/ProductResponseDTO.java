package com.sp.product.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductResponseDTO extends BaseAuditResponseDTO {

    private Long   productId;

    private String sku;

    private String productName;

    private String productDescription;

    private float  weight;

    private String weightUnit;

    private String dimension;

    private String imageUrl;

}
