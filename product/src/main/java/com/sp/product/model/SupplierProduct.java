package com.sp.product.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "supplier_product")
public class SupplierProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(nullable = false)
    private Long supplierId ;

    @Column(nullable = false)
    private String transactionCurrency;


}

