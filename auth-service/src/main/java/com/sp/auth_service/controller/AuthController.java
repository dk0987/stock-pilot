package com.sp.auth_service.controller;

import com.sp.auth_service.config.JWTUtil;
import com.sp.auth_service.dto.UserRequestDTO;
import com.sp.auth_service.dto.UserResponseDTO;
import com.sp.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    private ResponseEntity<UserResponseDTO> login(UserRequestDTO request) {
        UserResponseDTO authenticatedUser = authService.getAuthenticatedUser(request);
        if  (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtUtil.generateToken(authenticatedUser);
        authenticatedUser.setToken(token);

        return ResponseEntity.ok(authenticatedUser);

    }
}
