package com.sp.warehouse.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WarehouseResponseDTO extends BaseAuditResponseDTO {

    private Long               id;
    private String             warehouseName;
    private String             warehouseCode;
    private AddressResponseDTO address;
    private int                maxCapacity;


}
