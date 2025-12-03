package com.sp.warehouseservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock-history-table")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private UUID warehouseId;
    @NotNull
    private UUID productId;
    @NotNull
    private String changeType;
    @NotNull
    private int quantityChange;
    @NotNull
    private int newStockLevel;
    @NotNull
    private UUID relatedOrderId;
    @NotNull
    private UUID userId;
    private String notes;
    private LocalDateTime timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

    public void setQuantityChange(int quantityChange) {
        this.quantityChange = quantityChange;
    }

    public int getNewStockLevel() {
        return newStockLevel;
    }

    public void setNewStockLevel(int newStockLevel) {
        this.newStockLevel = newStockLevel;
    }

    public UUID getRelatedOrderId() {
        return relatedOrderId;
    }

    public void setRelatedOrderId(UUID relatedOrderId) {
        this.relatedOrderId = relatedOrderId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
