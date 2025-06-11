package com.sstinternaltools.sstinternal_tools.documents.dto;

import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import java.time.LocalDateTime;
import java.util.Set;

public class DocumentSummaryDto {

    private String title;
    private DocumentCategory category;
    private Set<String> tags;
    private String latestFilePath;
    private LocalDateTime uploadedAt;

    public DocumentSummaryDto(String title, DocumentCategory category, Set<String> tags, String latestFilePath, LocalDateTime uploadedAt) {
        this.title = title;
        this.category = category;
        this.tags = tags;
        this.latestFilePath = latestFilePath;
        this.uploadedAt = uploadedAt;
    }

    public DocumentSummaryDto() {}

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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getLatestFilePath() {
        return latestFilePath;
    }

    public void setLatestFilePath(String latestFilePath) {
        this.latestFilePath = latestFilePath;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
