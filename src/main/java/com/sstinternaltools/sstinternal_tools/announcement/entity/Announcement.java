package com.sstinternaltools.sstinternal_tools.announcement.entity;

import com.sstinternaltools.sstinternal_tools.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection(targetClass = AnnouncementLabel.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "announcement_labels",
            joinColumns = @JoinColumn(name = "announcement_id")
    )
    @Column(name = "label")
    @Enumerated(EnumType.STRING)
    private Set<AnnouncementLabel> labels = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean deleted = false;
    @Version
    private long version;

    protected Announcement() {}

    public Announcement(String title,
                        String description,
                        Set<AnnouncementLabel> labels,
                        User author) {
        this.title       = title;
        this.description = description;
        this.labels      = labels;
        this.author      = author;
        this.createdAt   = LocalDateTime.now();
        this.updatedAt   = this.createdAt;
    }

    public void update(String title,
                       String description,
                       Set<AnnouncementLabel> labels) {
        this.title       = title;
        this.description = description;
        this.labels      = labels;
        this.updatedAt   = LocalDateTime.now();
    }

    public void markDeleted() { this.deleted = true; }

    public Long getId()                   { return id; }
    public String getTitle()              { return title; }
    public String getDescription()        { return description; }
    public Set<AnnouncementLabel> getLabels() { return labels; }
    public User getAuthor()               { return author; }
    public LocalDateTime getCreatedAt()   { return createdAt; }
    public LocalDateTime getUpdatedAt()   { return updatedAt; }
    public boolean isDeleted()            { return deleted; }
}
