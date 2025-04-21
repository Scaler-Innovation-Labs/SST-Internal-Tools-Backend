package com.sstinternaltools.sstinternal_tools.announcement.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "announcement_labels")
public class AnnouncementLabel implements Serializable {
    @EmbeddedId
    private AnnouncementLabelId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("announcementId")
    private Announcement announcement;

    @Enumerated(EnumType.STRING)
    @Column(name = "label_type", nullable = false)
    private LabelType labelType;

    protected AnnouncementLabel() {
        // for JPA
    }

    public AnnouncementLabel(Announcement announcement, LabelType labelType) {
        this.announcement = announcement;
        this.labelType = labelType;
        this.id = new AnnouncementLabelId(announcement.getId(), labelType);
    }

    public AnnouncementLabelId getId() {
        return id;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnnouncementLabel)) return false;
        AnnouncementLabel that = (AnnouncementLabel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

