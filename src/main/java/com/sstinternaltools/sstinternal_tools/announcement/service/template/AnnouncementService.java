package com.sstinternaltools.sstinternal_tools.announcement.service.template;

import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementRequest;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementResponse;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementUpdateRequest;
import com.sstinternaltools.sstinternal_tools.announcement.model.AnnouncementLabel;
import org.springframework.data.domain.*;

import java.util.Set;

public interface AnnouncementService {

    AnnouncementResponse create(AnnouncementRequest dto);

    Page<AnnouncementResponse> search(Set<AnnouncementLabel> labels, Pageable pageable);

    AnnouncementResponse findById(Long id);

    AnnouncementResponse update(Long id, AnnouncementUpdateRequest dto);

    void delete(Long id);
}
