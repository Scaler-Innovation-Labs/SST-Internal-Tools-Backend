package com.sstinternaltools.sstinternal_tools.Issues.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TicketResponceDto {
    @NotBlank (message = "Title cannot be blank")
    private String title;
    @NotBlank (message = "Description cannot be blank")
    private String description;
    @NotBlank (message = "Priority cannot be blank")
    private String priority;
    @NotBlank (message = "Status cannot be blank")
    private String status;
    @NotBlank (message = "Campus cannot be blank")
    private String campus;
    @NotBlank (message = "Upvote cannot be blank")
    @NotNull (message = "Upvote cannot be blank")
    private long upvote;
    @NotBlank (message = "Image URL cannot be blank")
    private String imageUrl;
    @NotBlank (message = "Created by cannot be blank")
    private String createdBy;
    @NotBlank (message = "Created at cannot be blank")
    private String createdAt;
    @NotBlank (message = "Updated at cannot be blank")
    private String updatedAt;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public long getUpvote() {
        return upvote;
    }

    public void setUpvote(long upvote) {
        this.upvote = upvote;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
