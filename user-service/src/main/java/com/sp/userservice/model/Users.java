package com.sp.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true , nullable = false)
    private String username ;
    @Email
    @Column(unique = true , nullable = false )
    private String email;
    @Column(nullable = false )
    private String password;
    @Column(nullable = false )
    private String first_name;
    private String last_name;
    private String phone_number;

    // Default Fields
    private boolean isActive;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authority",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities = new HashSet<>();

}
