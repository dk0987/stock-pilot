// service/UsersService.java
package com.sp.userservice.service;

import com.sp.userservice.dto.UsersRequestDTO;
import com.sp.userservice.dto.UsersResponseDTO;
import com.sp.userservice.exceptions.*;
import com.sp.userservice.mapper.UsersMapper;
import com.sp.userservice.model.Authority;
import com.sp.userservice.model.Users;
import com.sp.userservice.repository.AuthorityRepository;
import com.sp.userservice.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    private final UsersRepository     userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder     passwordEncoder;


    public UsersService(UsersRepository userRepository,
                        AuthorityRepository authorityRepository,
                        PasswordEncoder passwordEncoder) {
        this.userRepository      = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder     = passwordEncoder;
    }

    @Transactional
    public UsersResponseDTO createUser(UsersRequestDTO request , Long performedBy) {
        log.info("Creating user: {}", request.getUserName());

        // Unique checks
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (!Objects.equals(request.getPassword(), request.getRePassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }
        // Authorities validation + fetch
        Set<Authority> authorities = fetchAndValidateAuthorities(request.getAuthorityIds());
        // Map -> entity
        Users user = UsersMapper.toUser(request, authorities);

        // Hash password
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedBy(performedBy);
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(Boolean.TRUE);

        Users saved = userRepository.save(user);
        log.info("User created with id: {}", saved.getId());
        return UsersMapper.toResponse(saved);
    }

    @Transactional
    public UsersResponseDTO updateUser(UsersRequestDTO request, Long id , Long performedBy) {
        log.info("Updating user id: {}", id);

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Email: only if provided and different
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new UserEmailUpdateException("Email already used");
            }
            user.setEmail(request.getEmail());
        }

        // Username: only if provided and different
        if (request.getUserName() != null && !request.getUserName().equals(user.getUserName())) {
            if (userRepository.existsByUserName(request.getUserName())) {
                throw new UserNameUpdateException("Username already used");
            }
            user.setUserName(request.getUserName());
        }

        // Simple fields
        Optional.ofNullable(request.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(user::setPhoneNumber);

        // isActive is Boolean in DTO (nullable) - apply only if provided
        if (request.getIsActive() != null) {
            user.setActive(request.getIsActive());
        }

        // Password update (if provided)
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            if (!Objects.equals(request.getPassword(), request.getRePassword())) {
                throw new PasswordMismatchException("Password mismatch");
            }
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Authorities update
        if (request.getAuthorityIds() != null && !request.getAuthorityIds().isEmpty()) {
            Set<Authority> authorities = fetchAndValidateAuthorities(request.getAuthorityIds());
            user.setAuthorities(authorities);
        }

        user.setUpdatedBy(performedBy);
        user.setUpdatedAt(LocalDateTime.now());
        Users saved = userRepository.save(user);
        log.info("User updated id: {}", saved.getId());
        return UsersMapper.toResponse(saved);
    }

    @Transactional
    public void updateUserStatus(Long id , Long performedBy) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setActive(!user.isActive());
        user.setUpdatedBy(performedBy);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        log.info("Toggled user active status for id: {} -> {}", id, user.isActive());
    }

    @Transactional
    private Set<Authority> fetchAndValidateAuthorities(Collection<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }

        // fetch from DB
        List<Authority> list = authorityRepository.findAllById(ids);

        if (list.size() != ids.size()) {
            // compute missing ids for better message
            Set<Long> found = list.stream().map(Authority::getId).collect(Collectors.toSet());
            Set<Long> missing = ids.stream().filter(i -> !found.contains(i)).collect(Collectors.toSet());
            throw new AuthoritiesNotFoundException("Authorities not found: " + missing);
        }
        return new HashSet<>(list);
    }

    @Transactional
    public Users authenticateUser(String userName, String email, String password) {

        if (password == null || password.isBlank()) {
            throw new PasswordMismatchException("Password is mandatory: " + password);
        }

        if ((email == null || email.isBlank()) && (userName == null || userName.isBlank())) {
            throw new UserEmailUpdateException("Email or Username is mandatory");
        }

        // Step 1: Find user
        Users dbUser = null;

        if (email != null && !email.isBlank()) {
            dbUser = getUserByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        }
        else {
            dbUser = getUserByUserName(userName)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        }

        // Step 2: Validate password
        log.info("Stored hash  : {}", dbUser.getPassword());
        log.info("Input raw pwd: {}", password);

        if (!passwordEncoder.matches(password, dbUser.getPassword())) {
            throw new PasswordMismatchException("Incorrect password");
        }

        log.info("Authenticating user with email: {}", dbUser.getUserName());
        return dbUser;

    }


    @Transactional
    private Optional<Users> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Transactional
    private Optional<Users> getUserByUserName(String userName) {
        return Optional.ofNullable(userRepository.findByUserName(userName));
    }
}
