package com.sp.inventory.repository;

import com.sp.inventory.model.StockThreshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StockThresholdRepository extends JpaRepository<StockThreshold, Long> {

    @Query(
            value = """
                    SELECT * FROM stock_threshold
                    where product_id = :productId
                    and   warehouse_id = :warehouseId
                    """,
            nativeQuery = true
    )
    Optional<StockThreshold> findThreshold(
            @Param("productId")   Long productId ,
            @Param("warehouseId") Long warehouseId
    );

    @Query(
            value = """
                    SELECT * FROM stock_threshold
                    where   warehouse_id in :warehouseId
                    """,
            nativeQuery = true
    )
    List<StockThreshold> findThresholdByWarehouseId(
            @Param("warehouseId") List<Long> warehouseId
    );


}
