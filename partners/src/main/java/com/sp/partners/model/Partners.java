package com.sp.partners.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "partners")
public class Partners extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false )
    @Min(value = 1)
    @Max(value = 2)
    private Long partnerTypeId;

    @Column(unique = true, nullable = false)
    private Long addressId;

    @Column(nullable = false)
    private String partnerName;

    private String notes;

}
