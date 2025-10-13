package com.sp.userservice.service;

import com.sp.userservice.dto.UserRequestDTO;
import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.exceptions.PasswordMismatchException;
import com.sp.userservice.exceptions.RoleNotFoundException;
import com.sp.userservice.exceptions.UserAlreadyExistsException;
import com.sp.userservice.exceptions.UserNotFoundException;
import com.sp.userservice.mapper.UserMapper;
import com.sp.userservice.model.CustomUser;
import com.sp.userservice.model.Roles;
import com.sp.userservice.repository.CustomUserRepository;
import com.sp.userservice.repository.RoleRepository;
import com.sp.userservice.util.RoleConverter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomUserService {
    private final CustomUserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomUserService(CustomUserRepository userRepository , RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserResponseDTO> getUsers(){
        List<CustomUser> users = userRepository.findAll();
        if (users.isEmpty()) {throw new UserNotFoundException("User not found");}
        //Return mapped user response dto
        return users.stream().map(UserMapper::toDTO).toList();
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // --- 1. Validate Email Existence
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + userRequestDTO.getEmail() + " already exists.");
        }
        // --- 2. Validate Password Match
        if (!Objects.equals(userRequestDTO.getPassword(), userRequestDTO.getRe_password())) {
            throw new PasswordMismatchException("Passwords do not match.");
        }
        //Get User Role name
        String role_name = RoleConverter.getRoleName(userRequestDTO.getRole());
        //Get User Role by role name
        Roles userRole = roleRepository.getByName(role_name);
        if(userRole == null) {throw new RoleNotFoundException("Role " + role_name + " not found.");}

        //Create User
        CustomUser user;
        user = UserMapper.toUser(userRequestDTO);
        user.setRole(userRole);
        user.setCreated_at(LocalDate.now());
        user.setIs_active(true);
        userRepository.save(user);
        return UserMapper.toDTO(user);

    }

}
