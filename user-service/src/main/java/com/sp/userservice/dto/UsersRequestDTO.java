package com.sp.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Set;

@Data
public class UsersRequestDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 100, message = "Username must be between 5 and 100 chars")
    private String userName;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 100, message = "First name can't exceed 100 chars")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;

    @NotBlank(message = "Re-enter password")
    private String rePassword;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Valid email is required")
    private String email;

    private String phoneNumber;

    @NotEmpty(message = "At least one authority must be assigned")
    private Set<Long> authorityIds;

    private Boolean isActive;
}
