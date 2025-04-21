package com.sstinternaltools.sstinternal_tools.announcement.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AnnouncementLabelId implements Serializable {
    @Column(name = "announcement_id")
    private Long announcementId;

    @Enumerated(EnumType.STRING)
    @Column(name = "label_type")
    private LabelType labelType;

    protected AnnouncementLabelId() {
        // for JPA
    }

    public AnnouncementLabelId(Long announcementId, LabelType labelType) {
        this.announcementId = announcementId;
        this.labelType = labelType;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnnouncementLabelId)) return false;
        AnnouncementLabelId that = (AnnouncementLabelId) o;
        return Objects.equals(announcementId, that.announcementId) && labelType == that.labelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(announcementId, labelType);
    }
}