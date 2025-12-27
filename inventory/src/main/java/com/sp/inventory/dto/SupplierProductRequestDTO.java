package com.sp.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierProductRequestDTO {

    @NotNull(message = "Supplier for the product is necessary")
    private Long supplierId;

    @NotNull(message = "Transaction Currency for the Product is mandatory")
    private String transactionCurrency;

}
