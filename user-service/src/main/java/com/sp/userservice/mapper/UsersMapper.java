package com.sp.userservice.mapper;

import com.sp.userservice.dto.AuthorityResponseDTO;
import com.sp.userservice.dto.UsersRequestDTO;
import com.sp.userservice.dto.UsersResponseDTO;
import com.sp.userservice.model.Authority;
import com.sp.userservice.model.Users;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersMapper {

    public static UsersResponseDTO toResponse(Users user) {
        UsersResponseDTO dto = new UsersResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setIsActive(user.isActive());
        dto.setLastLogin(user.getLastLogin());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setCreatedBy(user.getCreatedBy());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setUpdatedBy(user.getUpdatedBy());

        Set<AuthorityResponseDTO> authDTOs = user.getAuthorities().stream()
                .filter(Authority::isActive)
                .map(auth -> {
                    AuthorityResponseDTO a = new AuthorityResponseDTO();
                    a.setId(auth.getId());
                    a.setName(auth.getName());
                    a.setDescription(auth.getDescription());
                    return a;
                }).collect(Collectors.toSet());

        dto.setAuthorities(authDTOs);

        return dto;
    }

    public static Users toUser(UsersRequestDTO request, Set<Authority> authorities) {
        Users user = new Users();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setActive(request.getIsActive() != null ? request.getIsActive() : true);
        user.setAuthorities(new HashSet<>(authorities));
        return user;
    }
}
