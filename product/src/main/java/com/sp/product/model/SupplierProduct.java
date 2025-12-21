package com.sp.product.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(
        name = "supplier_product",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_supplier_product",
                        columnNames = {"product_id" , "supplier_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_supplier_product_supplier",
                        columnList = "supplier_id"
                ),
                @Index(
                        name = "idx_supplier_product_product",
                        columnList = "product_id"
                )
        }
)
public class SupplierProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId ;

    @Column(nullable = false)
    private String transactionCurrency;


}

