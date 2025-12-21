package com.sp.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "warehouse")
public class Warehouse extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(nullable = false)
    private String warehouseName ;

    @Column(nullable = false)
    private String warehouseCode ;

    @Column(nullable = false)
    private Long addressId ;

    @Column(nullable = false)
    private int maxCapacity;

}
