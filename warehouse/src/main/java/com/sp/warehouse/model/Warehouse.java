package com.sp.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(
        name = "warehouse",
        indexes = {
                @Index(
                        name = "idx_warehouse_warehouse_code",
                        columnList = "warehouse_code"
                )
        }
)
public class Warehouse extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "warehouse_id")
    private Long id ;

    @Column(nullable = false)
    private String warehouseName ;

    @Column(name = "warehouse_code", nullable = false , unique = true)
    private String warehouseCode ;

    @Column(nullable = false)
    private Long addressId ;

    @Column(nullable = false)
    private int maxCapacity;

}
