package com.sstinternaltools.sstinternal_tools.documents.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Document document;

    private int versionNumber;

    private String fileUrl; // Cloud URL or file path

    private LocalDateTime uploadedAt ;

    private Long uploadedByUserId;

    public DocumentVersion(Document document, int versionNumber, String fileUrl, Long uploadedByUserId) {
        this.document = document;
        this.versionNumber = versionNumber;
        this.fileUrl = fileUrl;
        this.uploadedByUserId = uploadedByUserId;
    }

    public DocumentVersion() {}

    public Long getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
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