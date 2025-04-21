package com.sstinternaltools.sstinternal_tools.announcement.repository;

import com.sstinternaltools.sstinternal_tools.announcement.entity.Announcement;
import com.sstinternaltools.sstinternal_tools.announcement.entity.LabelType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByLabels_LabelType(LabelType labelType);
}
