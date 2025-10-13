package com.sp.userservice.repository;

import com.sp.userservice.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Roles, UUID> {
    Roles getByName(String roleName);
}