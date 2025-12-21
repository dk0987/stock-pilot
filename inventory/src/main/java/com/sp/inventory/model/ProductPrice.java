package com.sp.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(
        name = "product_price",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_product_price",
                        columnNames = {"product_id" , "warehouse_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_product_price_product_id",
                        columnList = "product_id"
                ),
                @Index(
                        name = "idx_product_price_warehouse_id",
                        columnList = "warehouse_id"
                )
        }
)
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "product_id",nullable = false)
    private Long productId ;

    @Column(name = "warehouse_id",nullable = false)
    private Long warehouseId;

    @Column(nullable = false)
    @Min(value = 0)
    private BigDecimal unitPrice ;

}
