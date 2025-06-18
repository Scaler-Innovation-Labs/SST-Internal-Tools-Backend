package com.sstinternaltools.sstinternal_tools.announcement.dto;

import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record AnnouncementRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 255, message = "Title should not exceed 255 characters")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        Set<AnnouncementLabel> labels) {}
