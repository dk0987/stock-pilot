package com.sp.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "Username is mandatory field")
    @Size(max = 100 , min = 5 , message = "Username should be between 5 to 100 characters")
    private String username;

    @NotBlank(message = "First name is mandatory field")
    @Size(max = 100 , message = "Username can't exceed more than 100")
    private String first_name;
    private String last_name;

    @NotBlank(message = "Password is mandatory field")
    @Size(min = 8 , message = "Password Should be more than 8 digit")
    private String password;

    @NotBlank(message = "Re Password is mandatory field")
    private String re_password;

    @NotBlank(message = "Email is mandatory field")
    @Email(message = "Valid email is required")
    private String email;

    private String phone_number;
    private int role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRe_password() {
        return re_password;
    }

    public void setRe_password(String re_password) {
        this.re_password = re_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
