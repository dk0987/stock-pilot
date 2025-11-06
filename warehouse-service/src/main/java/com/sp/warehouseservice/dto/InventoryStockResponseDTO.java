package com.sp.warehouseservice.dto;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InventoryStockResponseDTO {
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
    public OffsetDateTime warehouseCreatedAt ;
    public List<StockDetailsResponseDTO> stockDetails ;

    public List<StockDetailsResponseDTO> getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(List<StockDetailsResponseDTO> stockDetails) {
        this.stockDetails = stockDetails;
    }

    public String getWarehouseEmail() {
        return warehouseEmail;
    }

    public void setWarehouseEmail(String warehouseEmail) {
        this.warehouseEmail = warehouseEmail;
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

    public OffsetDateTime getWarehouseCreatedAt() {
        return warehouseCreatedAt;
    }

    public void setWarehouseCreatedAt(OffsetDateTime warehouseCreatedAt) {
        this.warehouseCreatedAt = warehouseCreatedAt;
    }



}
