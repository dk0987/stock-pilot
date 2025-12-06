package com.sp.auth_service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserGRPCResponseDTO {
    private Long userId;
    private Set<Long> allowedAuthorities;
}
