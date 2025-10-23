package com.sp.warehouseservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory-stock")
public class InventoryStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID productId;
    private UUID warehouseId;
    private Integer quantity;
    private LocalDateTime updateTime;
}
