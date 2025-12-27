package com.sp.warehouse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequestDTO {

    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;

    @Email
    @NotNull(message = "Email is mandatory")
    private String email;
}
