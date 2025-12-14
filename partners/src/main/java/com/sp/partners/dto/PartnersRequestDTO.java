package com.sp.partners.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class PartnersRequestDTO {

    @NotNull(message = "Partners Name is mandatory")
    private String partnerName;

    private String notes;

    @NotNull(message = "Proper address is mandatory")
    private AddressRequestDTO address ;

}
