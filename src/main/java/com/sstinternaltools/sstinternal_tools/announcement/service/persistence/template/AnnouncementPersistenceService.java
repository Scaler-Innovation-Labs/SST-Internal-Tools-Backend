package com.sstinternaltools.sstinternal_tools.announcement.service.persistence.template;

import com.sstinternaltools.sstinternal_tools.announcement.entity.Announcement;
import com.sstinternaltools.sstinternal_tools.announcement.entity.LabelType;

import java.util.List;
import java.util.Optional;

public interface AnnouncementPersistenceService {
    Announcement save(Announcement announcement);
    Optional<Announcement> findById(Long id);
    List<Announcement> findAll();
    void deleteById(Long id);
    List<Announcement> findByLabelType(LabelType labelType);
}
