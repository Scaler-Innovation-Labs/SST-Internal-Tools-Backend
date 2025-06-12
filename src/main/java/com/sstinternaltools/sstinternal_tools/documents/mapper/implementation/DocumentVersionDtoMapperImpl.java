package com.sstinternaltools.sstinternal_tools.documents.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentVersionDtos.*;
import com.sstinternaltools.sstinternal_tools.documents.entity.Document;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentVersion;
import com.sstinternaltools.sstinternal_tools.documents.mapper.DocumentVersionDtoMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DocumentVersionDtoMapperImpl implements DocumentVersionDtoMapper {
    @Override
    public DocumentVersion fromCreateDto(DocumentVersionCreateDto dto, Document document, String fileUrl, Long versionNumber, Long uploadedByUserId) {
        DocumentVersion version = new DocumentVersion(document, versionNumber, fileUrl, uploadedByUserId);
        version.setUploadedAt(LocalDateTime.now()); // Set timestamp here
        return version;
    }

    @Override
    public DocumentVersionResponseDto toResponseDto(DocumentVersion version, String documentName, String uploaderEmail) {
        return new DocumentVersionResponseDto(
                version.getId(),
                documentName,
                version.getVersionNumber(),
                version.getFileUrl(),
                version.getUploadedAt(),
                uploaderEmail
        );
    }

    @Override
    public DocumentVersionSummaryDto toSummaryDto(DocumentVersion version, String documentName) {
        return new DocumentVersionSummaryDto(
                documentName,
                version.getVersionNumber(),
                version.getFileUrl(),
                version.getUploadedAt()
        );
    }

}
