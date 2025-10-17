package com.sp.userservice.controller;

import com.sp.userservice.dto.UserRequestDTO;
import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.dto.validators.CreateUserValidationGroup;
import com.sp.userservice.service.CustomUserService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Validated({Default.class , CreateUserValidationGroup.class}) @RequestBody UserRequestDTO userRequestDTO
    ) {
        return ResponseEntity.ok().body(userService.createUser(userRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable UUID id,
            @Validated(Default.class) @RequestBody UserRequestDTO userUpdateRequestDTO
    ) {
        return ResponseEntity.ok().body(userService.updateUser(userUpdateRequestDTO , id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.updateUserStatus(id);
    }

    @GetMapping("/log-in")
    public ResponseEntity<UserResponseDTO> login(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok().body(userService.login(email, password));
    }
}
