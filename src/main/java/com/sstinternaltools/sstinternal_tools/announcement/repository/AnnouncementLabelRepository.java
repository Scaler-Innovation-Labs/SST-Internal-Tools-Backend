package com.sstinternaltools.sstinternal_tools.announcement.repository;

import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabel;
import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabelId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementLabelRepository extends JpaRepository<AnnouncementLabel, AnnouncementLabelId> {
}

