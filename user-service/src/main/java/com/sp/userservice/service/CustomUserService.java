package com.sp.userservice.service;

import com.sp.userservice.dto.UserRequestDTO;
import com.sp.userservice.dto.UserResponseDTO;
import com.sp.userservice.exceptions.*;
import com.sp.userservice.mapper.UserMapper;
import com.sp.userservice.model.CustomUser;
import com.sp.userservice.model.Roles;
import com.sp.userservice.repository.CustomUserRepository;
import com.sp.userservice.repository.RoleRepository;
import com.sp.userservice.util.RoleConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO , UUID id) {
        String userRole = null ;
        //Existing User Check
        CustomUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        //User update request role existence check
        if (userRequestDTO.getRole() != 0){
            userRole = RoleConverter.getRoleName(userRequestDTO.getRole()).toUpperCase();
            //Updating role existence Check
            if (!roleRepository.existsByName(userRole)){
                throw new RoleNotFoundException("Role " + userRole + " not found.");
            }
        }

        //Updating Email existence check
        if (
                userRequestDTO.getEmail() != null &&
                !user.getEmail().equals(userRequestDTO.getEmail()) &&
                userRepository.existsByEmail(userRequestDTO.getEmail())
        ) {
            throw new UserEmailUpdateException("Email id is already registered");
        }
        //Updating username existence Check
        if (
                userRequestDTO.getUsername() != null &&
                !user.getUsername().equals(userRequestDTO.getUsername()) &&
                userRepository.existsByUsername(userRequestDTO.getUsername())
        ) {
            throw new UserNameUpdateException("Username is already registered");
        }

        CustomUser updatingUser = UserMapper.toUser(userRequestDTO);
        updatingUser.setId(id);
        updatingUser.setEmail(updatingUser.getEmail() == null ? user.getEmail() : updatingUser.getEmail());
        updatingUser.setUsername(updatingUser.getUsername() == null ? user.getUsername() : updatingUser.getUsername());
        updatingUser.setRole(userRole == null ? user.getRole() : roleRepository.getByName(userRole));
        updatingUser.setUpdated_at(LocalDate.now());
        updatingUser.setCreated_at(user.getCreated_at());
        updatingUser.setIs_active(user.isIs_active());

       try {
            userRepository.save(updatingUser);
            return UserMapper.toDTO(updatingUser);
        } catch (RuntimeException e) {
           throw new InternalError("Something Went Wrong  while updating the user");
       }
    }

    public void updateUserStatus(UUID id) {
        //Existing User Check
        CustomUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        try{
            user.setUpdated_at(LocalDate.now());
            user.setIs_active(!user.isIs_active());
            userRepository.save(user);
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
