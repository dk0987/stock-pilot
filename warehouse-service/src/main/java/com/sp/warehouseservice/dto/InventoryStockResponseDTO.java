package com.sp.warehouseservice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class InventoryStockResponseDTO {
    public List<UUID> id ;
    public UUID warehouse;
    public String warehouseName ;
    public String warehouseAddress ;
    public String warehouseCity    ;
    public String warehouseStates   ;
    public String warehouseZip     ;
    public String warehouseCountry ;
    public String warehousePhone   ;
    public String warehouseEmail   ;
    public UUID warehouseCreatedBy ;
    public LocalDateTime warehouseCreatedAt ;

    public List<UUID> getId() {
        return id;
    }

    public void setId(List<UUID> id) {
        this.id = id;
    }

    public UUID getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(UUID warehouse) {
        this.warehouse = warehouse;
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

    public String getWarehouseStates() {
        return warehouseStates;
    }

    public void setWarehouseStates(String warehouseStates) {
        this.warehouseStates = warehouseStates;
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

    public String getWarehousEmail() {
        return warehouseEmail;
    }

    public void setWarehousEmail(String warehousEmail) {
        this.warehouseEmail = warehousEmail;
    }

    public UUID getWarehouseCreatedBy() {
        return warehouseCreatedBy;
    }

    public void setWarehouseCreatedBy(UUID warehouseCreatedBy) {
        this.warehouseCreatedBy = warehouseCreatedBy;
    }

    public LocalDateTime getWarehouseCreatedAt() {
        return warehouseCreatedAt;
    }

    public void setWarehouseCreatedAt(LocalDateTime warehouseCreatedAt) {
        this.warehouseCreatedAt = warehouseCreatedAt;
    }



}
