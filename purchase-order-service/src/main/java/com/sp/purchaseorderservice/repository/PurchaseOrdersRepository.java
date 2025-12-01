package com.sp.purchaseorderservice.repository;

import com.sp.purchaseorderservice.model.PurchaseOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseOrdersRepository extends JpaRepository<PurchaseOrders, UUID> {
}
