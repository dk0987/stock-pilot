package com.sp.auth_service.service;

import com.sp.auth_service.dto.UserGRPCResponseDTO;
import com.sp.auth_service.dto.UserRequestDTO;
import com.sp.auth_service.dto.UserResponseDTO;
import com.sp.auth_service.grpc.UserServiceClient;
import com.sp.auth_service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserServiceClient userServiceClient;

    public AuthService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
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

        return UserMapper.toUserResponseDTO(authenticatedUser);

    }
}
