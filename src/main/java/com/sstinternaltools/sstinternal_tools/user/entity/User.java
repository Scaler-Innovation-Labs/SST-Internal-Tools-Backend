package com.sstinternaltools.sstinternal_tools.user.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Long id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String username, String email, String password, List<UserRole> userRoles,LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {}
}
