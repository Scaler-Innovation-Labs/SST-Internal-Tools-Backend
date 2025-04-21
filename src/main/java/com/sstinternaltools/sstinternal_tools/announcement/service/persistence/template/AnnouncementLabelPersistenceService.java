package com.sstinternaltools.sstinternal_tools.announcement.service.persistence.template;

import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabel;
import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabelId;

public interface AnnouncementLabelPersistenceService {
    AnnouncementLabel save(AnnouncementLabel label);
    void deleteById(AnnouncementLabelId id);
}

