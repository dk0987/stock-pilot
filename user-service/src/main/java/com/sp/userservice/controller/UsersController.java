package com.sp.userservice.controller;

import com.sp.userservice.dto.UsersRequestDTO;
import com.sp.userservice.dto.UsersResponseDTO;
import com.sp.userservice.dto.validators.CreateUsersValidationGroup;
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
    public ResponseEntity<List<UsersResponseDTO>> getUsers(){
        List<UsersResponseDTO> usersResponse = userService.getUsers();
        return ResponseEntity.ok().body(usersResponse);
    }

    @PostMapping
    public ResponseEntity<UsersResponseDTO> createUser(
            @Validated({Default.class , CreateUsersValidationGroup.class}) @RequestBody UsersRequestDTO usersRequestDTO
    ) {
        return ResponseEntity.ok().body(userService.createUser(usersRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateUser(
            @PathVariable UUID id,
            @Validated(Default.class) @RequestBody UsersRequestDTO userUpdateRequestDTO
    ) {
        return ResponseEntity.ok().body(userService.updateUser(userUpdateRequestDTO , id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.updateUserStatus(id);
    }

    @GetMapping("/log-in")
    public ResponseEntity<UsersResponseDTO> login(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok().body(userService.login(email, password));
    }
}
