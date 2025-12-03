package com.sp.warehouseservice.dto;

import java.util.UUID;

public class InventoryStockUpdateRequestDTO {
    private UUID inventoryStockId;
    private int quantity;

    public UUID getInventoryStockId() {
        return inventoryStockId;
    }

    public void setInventoryStockId(UUID inventoryStockId) {
        this.inventoryStockId = inventoryStockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
