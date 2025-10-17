package com.sp.userservice.mapper;

import com.sp.userservice.dto.UserRequestDTO;
import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.model.CustomUser;
import com.sp.userservice.util.RoleConverter;

public class UserMapper {

    //Mapping user entity to User response dto
    public static UserResponseDTO toDTO (CustomUser user){
        UserResponseDTO userDto = new UserResponseDTO();
        userDto.setId(user.getId().toString());
        userDto.setUser_name(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirst_name());
        userDto.setLastName(user.getLast_name());
        userDto.setPhoneNumber(user.getPhone_number());
        userDto.setCreatedDate(user.getCreated_at());
        userDto.setUpdatedDate(user.getUpdated_at());
        userDto.setActive(user.isIs_active());
        userDto.setRole(RoleConverter.getRoleCode(user.getRole()));
        userDto.setRole_description(user.getRole().getDescription());
        return userDto;
    }

    public static CustomUser toUser (UserRequestDTO userRequestDTO) {
        CustomUser user = new CustomUser();
        user.setFirst_name(userRequestDTO.getFirst_name());
        user.setEmail(userRequestDTO.getEmail());
        user.setUsername(userRequestDTO.getUsername());
        user.setLast_name(userRequestDTO.getLast_name());
        user.setPhone_number(userRequestDTO.getPhone_number());
        user.setPassword(userRequestDTO.getPassword());
        return user;
    }

}


