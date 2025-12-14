package com.sp.common.feature_address.dto;

import jakarta.validation.constraints.Email;
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
    private String email;

}
