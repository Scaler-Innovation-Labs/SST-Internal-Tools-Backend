package com.sstinternaltools.sstinternal_tools.Issues.dto.ticket;
import jakarta.validation.constraints.NotBlank;

public class TicketSummaryDto {
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
    private long upvote;

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

}
