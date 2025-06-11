package com.sstinternaltools.sstinternal_tools.documents.dto;

import com.sstinternaltools.sstinternal_tools.documents.entity.AllowedUsers;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import com.sstinternaltools.sstinternal_tools.documents.entity.Tag;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

public class DocumentResponseDto{
    private Long id;
    private String title;
    private DocumentCategory category;
    private Set<AllowedUsers> allowedUsers;
    private Set<Tag> tags;
    private String latestFilePath;
    private int versionNumber;
    private LocalDateTime uploadedAt;
    private String uploadedBy;

    public DocumentResponseDto(Long id, String title, DocumentCategory category, Set<AllowedUsers> allowedUsers, Set<Tag> tags, String latestFilePath, int versionNumber, LocalDateTime uploadedAt, String uploadedBy) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.allowedUsers = allowedUsers;
        this.tags = tags;
        this.latestFilePath = latestFilePath;
        this.versionNumber = versionNumber;
        this.uploadedAt = uploadedAt;
        this.uploadedBy = uploadedBy;
    }

    public DocumentResponseDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getLatestFilePath() {
        return latestFilePath;
    }

    public void setLatestFilePath(String latestFilePath) {
        this.latestFilePath = latestFilePath;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}
