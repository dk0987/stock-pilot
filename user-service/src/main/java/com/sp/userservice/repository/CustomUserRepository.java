package com.sp.userservice.repository;

import com.sp.userservice.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, UUID> {

    boolean existsByEmail(String email);
    CustomUser findByEmail(String email);
    boolean existsByUsername(String username);
}
