package com.sstinternaltools.sstinternal_tools.Issues.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "ID cannot be null")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;


    private String content;
    private LocalDateTime timestamp;


    public Comment(Long id, Ticket ticket, User author, String content, LocalDateTime timestamp) { // Changed id to Long
        this.id = id;
        this.ticket = ticket;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Comment() {
    }

    public Long getId() { // Changed return type to Long
        return id;
    }

    public void setId(Long id) { // Changed parameter to Long
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}