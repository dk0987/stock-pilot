package com.sp.userservice.service;

import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.mapper.UserMapper;
import com.sp.userservice.model.CustomUser;
import com.sp.userservice.repository.CustomUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService {
    private final CustomUserRepository userRepository;

    public CustomUserService(CustomUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getUsers(){
        List<CustomUser> users = userRepository.findAll();
        //Return mapped user response dto
        return users.stream().map(UserMapper::toDTO).toList();
    }

}
