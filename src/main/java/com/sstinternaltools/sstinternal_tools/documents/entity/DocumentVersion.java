package com.sstinternaltools.sstinternal_tools.documents.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Document document;

    private Long versionNumber;

    private String fileUrl; // Cloud URL or file path

    private LocalDateTime uploadedAt ;

    private Boolean isLatestVersion;

    private Long uploadedByUserId;

    public DocumentVersion(Document document,Long versionNumber, String fileUrl, Long uploadedByUserId,Boolean isLatestVersion) {
        this.document = document;
        this.versionNumber = versionNumber;
        this.fileUrl = fileUrl;
        this.uploadedByUserId = uploadedByUserId;
        this.isLatestVersion = isLatestVersion;
    }

    public DocumentVersion() {}

    public Boolean isLatestVersion() {
        return isLatestVersion;
    }

    public void setLatestVersion(Boolean latestVersion) {
        isLatestVersion = latestVersion;
    }

    public Long getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Long getUploadedByUserId() {
        return uploadedByUserId;
    }

    public void setUploadedByUserId(Long uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }
}