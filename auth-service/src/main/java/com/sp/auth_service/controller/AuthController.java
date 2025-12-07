package com.sp.auth_service.controller;

import com.sp.auth_service.dto.UserRequestDTO;
import com.sp.auth_service.dto.UserResponseDTO;
import com.sp.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private ResponseEntity<UserResponseDTO> login(@RequestBody  UserRequestDTO request) {
        UserResponseDTO authenticatedUser = authService.getAuthenticatedUser(request);
        if  (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(authenticatedUser);

    }

    @GetMapping("/validate")
    private ResponseEntity<String> validateToken(
            @RequestHeader("Authorization") String authHeader
    ) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}
