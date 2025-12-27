package com.sp.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseAuditResponseDTO {

    private boolean       isActive;
    private Long          createdBy;
    private LocalDateTime createdAt;
    private Long          updatedBy;
    private LocalDateTime updatedAt;

}
