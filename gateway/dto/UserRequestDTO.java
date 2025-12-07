package com.sp.auth_service.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String userName;
    private String password;
    private String email;
}
