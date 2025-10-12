package com.sp.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID role_id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany( mappedBy = "role",fetch = FetchType.LAZY)
    private Set<CustomUser> users;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return role_id;
    }

    public void setId(UUID role_id) {
        this.role_id = role_id;
    }

    public Set<CustomUser> getUsers() {
        return users;
    }

    public void setUsers(Set<CustomUser> users) {
        this.users = users;
    }



}
