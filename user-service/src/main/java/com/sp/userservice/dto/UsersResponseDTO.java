package com.sp.userservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class UsersResponseDTO {

    private Long id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime lastLogin;
    private Boolean isActive;

    private Set<AuthorityResponseDTO> authorities = new HashSet<>();

    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
