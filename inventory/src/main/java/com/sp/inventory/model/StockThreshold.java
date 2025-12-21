package com.sp.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(
        name = "stock_threshold",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_stock_threshold",
                        columnNames = {"product_id" , "warehouse_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_stock_threshold_product_id",
                        columnList = "product_id"
                ),
                @Index(
                        name = "idx_stock_threshold_warehouse_id",
                        columnList = "warehouse_id"
                )
        }
)
public class StockThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "product_id", nullable = false)
    private Long productId ;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(nullable = false)
    @Min(value = 0)
    private int minQuantity;

    @Column(nullable = false)
    @Min(value = 0)
    private int maxQuantity;

}
