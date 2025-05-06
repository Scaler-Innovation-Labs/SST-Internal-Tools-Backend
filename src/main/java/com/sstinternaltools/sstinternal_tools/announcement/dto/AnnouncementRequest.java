package com.sstinternaltools.sstinternal_tools.announcement.dto;

import com.sstinternaltools.sstinternal_tools.announcement.model.AnnouncementLabel;
import java.util.Set;

public record AnnouncementRequest(
        String title,
        String description,
        Set<AnnouncementLabel> labels) {}