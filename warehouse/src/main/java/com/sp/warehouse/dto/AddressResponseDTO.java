package com.sp.warehouse.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AddressResponseDTO {

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
