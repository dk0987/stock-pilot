package com.sp.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "stock_threshold")
public class StockThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(nullable = false)
    private Long productId ;

    @Column(nullable = false)
    private Long warehouseId;

    @Column(nullable = false)
    @Min(value = 0)
    private int minQuantity;

    @Column(nullable = false)
    private int maxQuantity;


}
