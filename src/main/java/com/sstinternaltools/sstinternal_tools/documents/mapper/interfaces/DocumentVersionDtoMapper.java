package com.sstinternaltools.sstinternal_tools.documents.mapper;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentVersionDtos.*;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentVersion;
import com.sstinternaltools.sstinternal_tools.documents.entity.Document;

public interface DocumentVersionDtoMapper {
    DocumentVersion fromCreateDto(DocumentVersionCreateDto dto, Document document, String fileUrl, Long versionNumber, Long uploadedByUserId);
    DocumentVersionResponseDto toResponseDto(DocumentVersion version, String documentName, String uploaderEmail);
    DocumentVersionSummaryDto toSummaryDto(DocumentVersion version, String documentName);
}
