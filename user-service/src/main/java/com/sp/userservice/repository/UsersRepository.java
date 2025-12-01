package com.sp.userservice.repository;

import com.sp.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomUserRepository extends JpaRepository<Users, UUID> {

    boolean existsByEmail(String email);
    Users findByEmail(String email);
    boolean existsByUsername(String username);
}
