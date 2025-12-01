package com.sp.userservice.mapper;

import com.sp.userservice.dto.UsersRequestDTO;
import com.sp.userservice.dto.UsersResponseDTO;
import com.sp.userservice.model.Users;
import com.sp.userservice.util.RoleConverter;

public class UserMapper {

    //Mapping user entity to User response dto
    public static UsersResponseDTO toDTO (Users users){
        UsersResponseDTO userDto = new UsersResponseDTO();
        userDto.setId(users.getId().toString());
        userDto.setUser_name(users.getUsername());
        userDto.setEmail(users.getEmail());
        userDto.setFirstName(users.getFirst_name());
        userDto.setLastName(users.getLast_name());
        userDto.setPhoneNumber(users.getPhone_number());
        userDto.setCreatedDate(users.getCreated_at());
        userDto.setUpdatedDate(users.getUpdated_at());
        userDto.setActive(users.isIs_active());
        userDto.setRole(RoleConverter.getRoleCode(users.getRole()));
        userDto.setRole_description(users.getRole().getDescription());
        return userDto;
    }

    public static Users toUser (UsersRequestDTO usersRequestDTO) {
        Users users = new Users();
        users.setFirst_name(usersRequestDTO.getFirst_name());
        users.setEmail(usersRequestDTO.getEmail());
        users.setUsername(usersRequestDTO.getUsername());
        users.setLast_name(usersRequestDTO.getLast_name());
        users.setPhone_number(usersRequestDTO.getPhone_number());
        users.setPassword(usersRequestDTO.getPassword());
        return users;
    }

}


