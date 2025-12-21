package com.sp.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product_price")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(nullable = false)
    private Long productId ;

    @Column(nullable = false)
    private Long warehouseId;

    @Column(nullable = false)
    private float unitPrice ;

}
