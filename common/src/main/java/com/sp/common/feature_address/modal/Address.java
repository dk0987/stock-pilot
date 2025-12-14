package com.sp.common.feature_address.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    @Email
    private String email;

}
