package com.sstinternaltools.sstinternal_tools.documents.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String code; // e.g. EXAM_POLICY_2024

    @ManyToOne(optional = false)
    private DocumentCategory category;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<AllowedUsers> allowedUsers;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tag> tags;
}
