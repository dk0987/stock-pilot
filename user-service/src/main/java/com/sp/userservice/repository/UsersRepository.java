package com.sp.userservice.repository;

import com.sp.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    Users   findByEmail(String email);
    boolean existsByUserName(String username);
}
