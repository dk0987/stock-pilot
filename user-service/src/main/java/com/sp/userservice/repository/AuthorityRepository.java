package com.sp.userservice.repository;

import com.sp.userservice.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority getByName(String roleName);
    Boolean   existsByName(String roleName);
}