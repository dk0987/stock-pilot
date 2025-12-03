package com.sp.warehouseservice.repository;

import com.sp.warehouseservice.model.InventoryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryStockRepository extends JpaRepository<InventoryStock, UUID> {
    List<InventoryStock> getByWarehouseId(UUID warehouseId);
    boolean existsByWarehouseId(UUID warehouseId);
}
