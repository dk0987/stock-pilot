package com.sp.auth_service.controller;

import com.sp.auth_service.dto.UserRequestDTO;
import com.sp.auth_service.dto.UserResponseDTO;
import com.sp.auth_service.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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
    private ResponseEntity<Map<String,Object>> validateToken(
            @RequestHeader("Authorization") String authHeader
    ) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);
        Map<String,Object> body = new HashMap<>();

        if (authService.validateToken(token)){
            body.put("valid", true);
            body.put("userId", authService.getUserId(token));
            body.put("roles", authService.getAuthority(token)); // will be serialized as JSON array of numbers
            return ResponseEntity.ok(body);
        } else {
            body.put("valid", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
