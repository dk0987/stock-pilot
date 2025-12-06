package com.sp.userservice.controller;

import com.sp.userservice.dto.UsersRequestDTO;
import com.sp.userservice.dto.UsersResponseDTO;
import com.sp.userservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersResponseDTO> createUser(@Valid @RequestBody UsersRequestDTO request){
        return ResponseEntity.ok(usersService.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UsersRequestDTO request){
        return ResponseEntity.ok(usersService.updateUser(request, id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleUserStatus(@PathVariable Long id){
        usersService.updateUserStatus(id);
        return ResponseEntity.noContent().build();
    }
}
