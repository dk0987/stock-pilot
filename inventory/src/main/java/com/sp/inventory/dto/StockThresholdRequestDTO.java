package com.sp.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockThresholdRequestDTO {

    @NotNull(message = "Warehouse is necessary to move product ")
    private Long warehouseId;

    @NotNull(message = "Min quantity for the product in a warehouse is mandatory")
    @Min(value = 0 , message = "Min quantity of the product in warehouse can't be negative")
    private int minQuantity;

    @NotNull(message = "Max quantity for the product in a warehouse is mandatory")
    @Min(value = 0 , message = "Max quantity of the product in warehouse can't be negative")
    private int maxQuantity;

}
