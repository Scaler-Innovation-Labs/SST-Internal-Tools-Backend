package com.sstinternaltools.sstinternal_tools.announcement.repository;

import com.sstinternaltools.sstinternal_tools.announcement.entity.Announcement;
import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabel;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long>,
        JpaSpecificationExecutor<Announcement> {

    Page<Announcement> findDistinctByDeletedFalse(Pageable pageable);

    Page<Announcement> findDistinctByLabelsInAndDeletedFalse(Set<AnnouncementLabel> labels,
                                                             Pageable pageable);
}
