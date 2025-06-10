package com.sstinternaltools.sstinternal_tools.documents.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<AllowedUsers> allowedUsers;
    @ManyToMany
    private Set<Tag> tags;

}
