package com.sp.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product")
public class Product extends BaseAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(nullable = false)
    private String name ;

    @Column(nullable = false , unique = true)
    private String sku;

    private String description ;

    @Column(nullable = false)
    private float weight;

    @Column(nullable = false)
    private String weightUnit;

    private String dimension;
    private String imageUrl;

}
