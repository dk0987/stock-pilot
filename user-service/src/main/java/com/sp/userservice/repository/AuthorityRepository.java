package com.sp.userservice.repository;

import com.sp.userservice.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Authority, UUID> {
    Authority getByName(String roleName);
    Boolean existsByName(String roleName);
}