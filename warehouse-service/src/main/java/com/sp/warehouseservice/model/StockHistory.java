package com.sp.warehouseservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock-history-table")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID warehouseId;
    private UUID productId;
    private String changeType;
    private int quantityChange;
    private int newStockLevel;
    private UUID relatedOrderId;
    private UUID userId;
    private String notes;
    private LocalDateTime timestamp;
}
