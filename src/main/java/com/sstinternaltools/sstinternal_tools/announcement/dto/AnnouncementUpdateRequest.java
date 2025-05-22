package com.sstinternaltools.sstinternal_tools.announcement.dto;

import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabel;

import java.util.Set;

public record AnnouncementUpdateRequest(
        String title,
        String description,
        Set<AnnouncementLabel> labels) {}