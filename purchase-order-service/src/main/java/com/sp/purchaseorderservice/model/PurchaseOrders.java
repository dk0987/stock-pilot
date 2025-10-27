package com.sp.purchaseorderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prchase-order")
public class PurchaseOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotNull
    private UUID supplierId;
    private LocalDateTime orderDate;
    @NotNull
    private LocalDateTime expectedDeliveryDate;
    @NotNull
    private UUID warehouseId;
    @NotNull
    private double totalAmount;
    private String status;
    private UUID createdBy;
    private UUID approvedUserId;
    private LocalDateTime approvedDate;
    private String rejectionReason;
    private String notes;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
