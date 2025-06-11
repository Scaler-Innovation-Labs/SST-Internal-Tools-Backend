package com.sstinternaltools.sstinternal_tools.documents.dto;

import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import java.time.LocalDateTime;
import java.util.Set;

public class DocumentSummaryDto {

    private String title;
    private DocumentCategory category;
    private Set<String> tags;
    private String latestFilePath;
    private LocalDateTime uploadedAt;

}
