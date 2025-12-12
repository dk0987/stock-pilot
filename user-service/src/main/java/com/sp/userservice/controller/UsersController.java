package com.sp.userservice.controller;

import com.sp.userservice.dto.UsersRequestDTO;
import com.sp.userservice.dto.UsersResponseDTO;
import com.sp.userservice.service.UsersService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/user")
    public ResponseEntity<UsersResponseDTO> createUser(
            @Valid @RequestBody UsersRequestDTO request,
            @RequestHeader("X-User-Id") Long performedBy
    ){
        return ResponseEntity.ok(usersService.createUser(request , performedBy));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UsersRequestDTO request,
            @RequestHeader("X-User-Id") Long performedBy
    ){
        return ResponseEntity.ok(usersService.updateUser(request, id, performedBy));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleUserStatus(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long performedBy
    ){
        usersService.updateUserStatus(id,performedBy);
        return ResponseEntity.noContent().build();
    }
}
