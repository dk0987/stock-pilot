package com.sp.purchaseorderservice.repository;

import com.sp.purchaseorderservice.model.PurchaseOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseOrderItemsRepository extends JpaRepository<PurchaseOrderItems, UUID> {
}
