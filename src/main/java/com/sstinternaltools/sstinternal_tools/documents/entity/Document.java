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

    public Document(String title, String code, DocumentCategory category, Set<AllowedUsers> allowedUsers, Set<Tag> tags) {
        this.title = title;
        this.code = code;
        this.category = category;
        this.allowedUsers = allowedUsers;
        this.tags = tags;
    }

    public Document() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DocumentCategory getCategory() {
        return category;
    }

    public void setCategory(DocumentCategory category) {
        this.category = category;
    }

    public Set<AllowedUsers> getAllowedUsers() {
        return allowedUsers;
    }

    public void setAllowedUsers(Set<AllowedUsers> allowedUsers) {
        this.allowedUsers = allowedUsers;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
