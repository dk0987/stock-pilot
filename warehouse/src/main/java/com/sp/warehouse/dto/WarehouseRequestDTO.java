package com.sp.warehouse.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WarehouseRequestDTO {

    @NotNull(message = "Warehouse name is mandatory")
    private String warehouseName;

    @NotNull(message = "Warehouse code is mandatory")
    private String warehouseCode;

    @NotNull(message = "Warehouse Address is mandatory")
    private AddressRequestDTO address;

    @NotNull(message = "Capacity is mandatory")
    @Min(value = 0 , message = "Negative value is not acceptable")
    private int maxCapacity;

}
