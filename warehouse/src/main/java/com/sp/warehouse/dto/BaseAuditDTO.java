package com.sp.warehouse.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseAuditDTO {

    private boolean       isActive;
    private Long          createdBy;
    private LocalDateTime createdAt;
    private Long          updatedBy;
    private LocalDateTime updatedAt;

}
