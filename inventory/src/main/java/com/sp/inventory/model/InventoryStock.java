package com.sp.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(
        name = "inventory_stock",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_inventory_stock",
                        columnNames = {"product_id" , "warehouse_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_inventory_stock_product_id",
                        columnList = "product_id"
                ),
                @Index(
                        name = "idx_inventory_stock_warehouse_id",
                        columnList = "warehouse_id"
                )
        }
)
public class InventoryStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "product_id",nullable = false)
    private Long productId ;

    @Column(name = "warehouse_id",nullable = false)
    private Long warehouseId;

    @Column(nullable = false)
    @Min(value = 0)
    private int currentStock;

}
