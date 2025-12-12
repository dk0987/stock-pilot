package com.sp.auth_service.service;

import com.sp.auth_service.config.JWTUtil;
import com.sp.auth_service.dto.UserGRPCResponseDTO;
import com.sp.auth_service.dto.UserRequestDTO;
import com.sp.auth_service.dto.UserResponseDTO;
import com.sp.auth_service.grpc.UserServiceClient;
import com.sp.auth_service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class AuthService {

    private final UserServiceClient userServiceClient;
    private final JWTUtil jwtUtil;

    public AuthService(
            UserServiceClient userServiceClient,
            JWTUtil jwtUtil
    ) {
        this.userServiceClient = userServiceClient;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDTO getAuthenticatedUser(UserRequestDTO request) {

        UserGRPCResponseDTO authenticatedUser = userServiceClient.getAuthenticatedUser(
                request.getUserName(),
                request.getEmail(),
                request.getPassword()
        );

        if (authenticatedUser == null) {
            throw new RuntimeException("User not found");
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(authenticatedUser.getUserId());
        userResponseDTO.setAllowedAuthorities(authenticatedUser.getAllowedAuthorities());

        String token  = jwtUtil.generateToken(userResponseDTO);
        return UserMapper.toUserResponseDTO(authenticatedUser , token);

    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        return jwtUtil.extractUserIdFromToken(token);
    }

    public Set<Long> getAuthority(String token) {
        return jwtUtil.extractAllowedAuthorities(token);
    }


}
