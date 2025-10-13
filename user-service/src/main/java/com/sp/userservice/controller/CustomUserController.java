package com.sp.userservice.controller;

import com.sp.userservice.dto.UserRequestDTO;
import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.service.CustomUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok().body(userService.createUser(userRequestDTO));
    }
}
