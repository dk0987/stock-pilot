package com.sp.warehouseservice.dto;

import java.util.UUID;

public class WarehouseRequestDTO {
    private UUID warehouseId;
    private String warehouseName;
    private String warehouseAddress;
    private String warehouseCity;
    private String warehouseState;
    private String warehouseZip;
    private String warehouseCountry;
    private String warehousePhone;
    private String warehouseEmail;
    private UUID   managedBy;

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseCity() {
        return warehouseCity;
    }

    public void setWarehouseCity(String warehouseCity) {
        this.warehouseCity = warehouseCity;
    }

    public String getWarehouseState() {
        return warehouseState;
    }

    public void setWarehouseState(String warehouseState) {
        this.warehouseState = warehouseState;
    }

    public String getWarehouseZip() {
        return warehouseZip;
    }

    public void setWarehouseZip(String warehouseZip) {
        this.warehouseZip = warehouseZip;
    }

    public String getWarehouseCountry() {
        return warehouseCountry;
    }

    public void setWarehouseCountry(String warehouseCountry) {
        this.warehouseCountry = warehouseCountry;
    }

    public String getWarehousePhone() {
        return warehousePhone;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone = warehousePhone;
    }

    public String getWarehouseEmail() {
        return warehouseEmail;
    }

    public void setWarehouseEmail(String warehouseEmail) {
        this.warehouseEmail = warehouseEmail;
    }

    public UUID getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(UUID managedBy) {
        this.managedBy = managedBy;
    }
}
