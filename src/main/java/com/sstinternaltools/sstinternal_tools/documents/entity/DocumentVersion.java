package com.sstinternaltools.sstinternal_tools.documents.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_version_id", nullable = false)
    private Long versionId;
    @ManyToOne
    @JoinColumn(name="document_id")
    private Document document;
    @ElementCollection
    @Enumerated(EnumType.STRING)

    private Set<AllowedUsers> allowedUsers;
    @ManyToMany
    private Set<Tag> tags;

}
