package com.sstinternaltools.sstinternal_tools.announcement.dto;


import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabel;
import java.time.LocalDateTime;
import java.util.Set;

public record AnnouncementResponse(
        Long id,
        String title,
        String description,
        Set<AnnouncementLabel> labels,
        String authorEmail,
        LocalDateTime createdAt) {}

