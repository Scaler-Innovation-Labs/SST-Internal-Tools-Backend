package com.sstinternaltools.sstinternal_tools.documents.dto;

import com.sstinternaltools.sstinternal_tools.documents.entity.AllowedUsers;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import com.sstinternaltools.sstinternal_tools.documents.entity.Tag;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

public class DocumentResponseDto{
    private Long id;
    private String title;
    private DocumentCategory category;
    private Set<AllowedUsers> allowedUsers;
    private Set<Tag> tags;
    private String latestFilePath;
    private int versionNumber;
    private LocalDateTime uploadedAt;
    private String uploadedBy;



}
