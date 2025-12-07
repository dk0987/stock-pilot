package com.sp.auth_service.mapper;

import com.sp.authGrpcService.User;
import com.sp.auth_service.dto.UserGRPCResponseDTO;
import com.sp.auth_service.dto.UserResponseDTO;

import java.util.HashSet;

public class UserMapper {

    public static UserGRPCResponseDTO toUserResponseDTO(User user) {
        UserGRPCResponseDTO userGRPCResponseDTO = new UserGRPCResponseDTO();
        userGRPCResponseDTO.setUserId(user.getId());
        userGRPCResponseDTO.setAllowedAuthorities(new HashSet<>(user.getAuthorityList()));
        return userGRPCResponseDTO;
    }

    public static UserResponseDTO toUserResponseDTO(UserGRPCResponseDTO user , String token) {
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setUserId(user.getUserId());
        userResponse.setAllowedAuthorities(new HashSet<>(user.getAllowedAuthorities()));
        userResponse.setToken(token);
        return userResponse;
    }


}
