package com.sp.purchaseorderservice.repository;

import com.sp.purchaseorderservice.model.PurchaseOrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseOrderApprovalRepository  extends JpaRepository<PurchaseOrderApproval , UUID> {
}
