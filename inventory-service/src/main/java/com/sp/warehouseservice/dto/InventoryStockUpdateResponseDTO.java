package com.sp.warehouseservice.dto;

import java.util.UUID;

public class InventoryStockUpdateResponseDTO {
    private UUID inventoryStockId;
    private UUID warehouseId;
    private UUID productId;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getInventoryStockId() {
        return inventoryStockId;
    }

    public void setInventoryStockId(UUID inventoryStockId) {
        this.inventoryStockId = inventoryStockId;
    }
}
