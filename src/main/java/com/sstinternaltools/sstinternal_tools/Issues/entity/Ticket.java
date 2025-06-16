package com.sstinternaltools.sstinternal_tools.Issues.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column; // Add this import



@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false) // Add this annotation
    private Long id;
    private String title;
    private String description;
    private TicketPriority priority;
    private TicketStatus status;

    private CampusType campus;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imageUrl;
    private long upvote;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdby;



    public Ticket(String title, String description, TicketPriority priority, TicketStatus status, CampusType campus, List<String> imageUrl, long upvote, LocalDateTime createdAt, LocalDateTime updatedAt, User createdby) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.campus = campus;
        this.imageUrl = imageUrl;
        this.upvote = upvote;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdby = createdby;
    }

    public Ticket() {
    }



    public Long getId() { // Changed return type from long to Long
        return id;
    }

    public void setId(Long id) { // Changed parameter type from long to Long
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
    public TicketPriority getPriority() {
        return priority;
    }
    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }
    public CampusType getCampus() {
        return campus;
    }
    public void setCampus(CampusType campus) {
        this.campus = campus;
    }
    public List<String> getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
    public long getUpvote() {
        return upvote;
    }
    public void setUpvote(long upvote) {
        this.upvote = upvote;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public User getCreatedby() {
        return createdby;
    }
    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }




}