package com.sp.warehouseservice.dto;

import java.util.UUID;

public class InventoryStockRequestDTO {
    private UUID warehouse;
    private UUID product;
    private int quantity;

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }

    public UUID getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(UUID warehouse) {
        this.warehouse = warehouse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
