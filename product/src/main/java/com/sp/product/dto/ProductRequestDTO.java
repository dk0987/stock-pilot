package com.sp.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class ProductRequestDTO {

    @NotNull(message = "Supplier detail for a product is mandatory")
    private SupplierProductRequestDTO supplierProductDetail;

    @NotNull(message = "Warehouse is necessary to move product ")
    private Set<WarehouseRequestDTO> warehouseIds;

    @NotNull(message = "Product name is mandatory")
    private String name;

    @NotNull(message = "Product sku is mandatory")
    private String sku;

    private String description;

    @NotNull(message = "Product Weight is mandatory")
    private float weight;

    @NotNull(message = "Product Weight unit is mandatory")
    private String weightUnit;

    private String dimension;

    private String imageUrl;

}
