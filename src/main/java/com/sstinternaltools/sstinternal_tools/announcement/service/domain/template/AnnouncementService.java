package com.sstinternaltools.sstinternal_tools.announcement.service.domain.template;


import com.sstinternaltools.sstinternal_tools.announcement.entity.Announcement;
import com.sstinternaltools.sstinternal_tools.announcement.entity.LabelType;
import java.util.List;
import java.util.Optional;

public interface AnnouncementService {
    Announcement createAnnouncement(Announcement announcement, List<LabelType> labels);
    Optional<Announcement> getAnnouncementById(Long id);
    List<Announcement> getAllAnnouncements();
    Announcement updateAnnouncement(Announcement announcement, List<LabelType> labels);
    void deleteAnnouncement(Long id);
    List<Announcement> getAnnouncementsByLabel(LabelType labelType);
}
