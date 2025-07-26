package com.sstinternaltools.sstinternal_tools.Issues.dto.ticket;

import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketPriority;
import com.sstinternaltools.sstinternal_tools.Issues.entity.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TicketResponseDto {
    @NotBlank (message = "Ticket ID cannot be blank")
    private Long id;
    @NotBlank (message = "Title cannot be blank")
    private String title;
    @NotBlank (message = "Description cannot be blank")
    private String description;
    @NotBlank (message = "Priority cannot be blank")
    private TicketPriority priority;
    @NotBlank (message = "Status cannot be blank")
    private TicketStatus status;
    @NotBlank (message = "Campus cannot be blank")
    private String campus;
    @NotBlank (message = "Upvote cannot be blank")
    @NotNull (message = "Upvote cannot be blank")
    private long upvote;
    @NotBlank (message = "Image URL cannot be blank")
    private List<String> imageUrl;
    @NotBlank (message = "Created by cannot be blank")
    private String createdBy;
    @NotBlank (message = "Created at cannot be blank")
    private String createdAt;
    @NotBlank (message = "Updated at cannot be blank")
    private String updatedAt;
    private boolean isPrivate = false; // false = public, true = private


    public TicketResponseDto(Long id, String title, String description, TicketPriority priority, TicketStatus status, String campus, long upvote, List<String> imageUrl, String createdBy, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.campus = campus;
        this.upvote = upvote;
        this.imageUrl = imageUrl;
        this.createdBy = createdBy;

    }
    public TicketResponseDto() {
    }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
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

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
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

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }


}
