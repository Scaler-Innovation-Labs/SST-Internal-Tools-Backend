package com.sstinternaltools.sstinternal_tools.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;
    private String username;
    private String email;
    private String password;
}
