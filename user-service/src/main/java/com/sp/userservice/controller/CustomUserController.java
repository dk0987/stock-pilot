package com.sp.userservice.controller;

import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.service.CustomUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users") //http:localhost:4000/users
public class CustomUserController {
    private final CustomUserService userService ;

    public CustomUserController(CustomUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        List<UserResponseDTO> usersResponse = userService.getUsers();
        return ResponseEntity.ok().body(usersResponse);
    }
}
