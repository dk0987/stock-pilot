package com.sp.partners.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PartnersResponseDTO {

    private Long id;

    @Min(value = 1)
    @Max(value = 2)
    private int partnerTypeId;
    private String partnerType;
    private String partnerName;
    private String notes;
    private AddressResponseDTO address ;
    private boolean isActive;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;

}
