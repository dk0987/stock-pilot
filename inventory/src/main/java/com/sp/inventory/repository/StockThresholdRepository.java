package com.sp.inventory.repository;

import com.sp.inventory.model.StockThreshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

}
