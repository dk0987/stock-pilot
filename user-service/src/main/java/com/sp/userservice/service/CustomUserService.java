package com.sp.userservice.service;

import com.sp.userservice.dto.UserRequestDTO;
import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.dto.UserUpdateRequestDTO;
import com.sp.userservice.exceptions.*;
import com.sp.userservice.mapper.UserMapper;
import com.sp.userservice.model.CustomUser;
import com.sp.userservice.model.Roles;
import com.sp.userservice.repository.CustomUserRepository;
import com.sp.userservice.repository.RoleRepository;
import com.sp.userservice.util.RoleConverter;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.net.HttpRetryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
        try {
            userRepository.save(user);
            return UserMapper.toDTO(user);
        } catch (RuntimeException e) {
            throw new InternalError("Something went wrong while saving the user");
        }
    }

    public UserResponseDTO updateUser(UserUpdateRequestDTO userRequestDTO , UUID id) {
        String userRole = RoleConverter.getRoleName(userRequestDTO.getRole()).toUpperCase();
        //Existing User Check
        CustomUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        //Updating Email existence check
        if (
                !user.getEmail().equals(userRequestDTO.getEmail()) &&
                userRepository.existsByEmail(userRequestDTO.getEmail())
        ) {
            throw new UserEmailUpdateException("Email id is already registered");
        }
        //Updating username existence Check
        if (
                !user.getUsername().equals(userRequestDTO.getUsername()) &&
                userRepository.existsByUsername(userRequestDTO.getUsername())
        ) {
            throw new UserNameUpdateException("Username is already registered");
        }
        //Updating role existence Check
        if (
                !user.getRole().getName().toUpperCase().equals(userRole) &&
                !roleRepository.existsByName(userRole)
        ){
            throw new RoleNotFoundException("Role " + userRole + " not found.");
        }

        CustomUser updatingUser = UserMapper.toUser(userRequestDTO);
        updatingUser.setRole(roleRepository.getByName(userRole));
        updatingUser.setUpdated_at(LocalDate.now());

       try {
            userRepository.save(updatingUser);
            return UserMapper.toDTO(updatingUser);
        } catch (RuntimeException e) {
           throw new InternalError("Something Went Wrong  while updating the user");
       }
    }

    public boolean updateUserStatus(UUID id) {
        //Existing User Check
        CustomUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        try{
            user.setUpdated_at(LocalDate.now());
            user.setIs_active(!user.isIs_active());
            userRepository.save(user);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public UserResponseDTO login(String email, String password) {
        //Existing User Check
        CustomUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }
        if (!user.getPassword().equals(password)) {
            throw new PasswordMismatchException("Passwords do not match.");
        }
        return UserMapper.toDTO(userRepository.findByEmail(email));
    }
}
