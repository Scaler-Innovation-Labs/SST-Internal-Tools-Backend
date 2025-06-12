package com.sstinternaltools.sstinternal_tools.documents.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.*;
import com.sstinternaltools.sstinternal_tools.documents.entity.Document;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentVersion;
import com.sstinternaltools.sstinternal_tools.documents.entity.Tag;
import com.sstinternaltools.sstinternal_tools.documents.mapper.interfaces.DocumentDtoMapper;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DocumentDtoMapperImpl implements DocumentDtoMapper {

    @Override
    public Document toEntity(DocumentCreateDto dto) {
        Document document = new Document();
        document.setTitle(dto.getTitle());
        document.setCategory(dto.getCategory());
        document.setTags(dto.getTags());
        document.setAllowedUsers(dto.getUserAllowed());
        return document;
    }

    @Override
    public DocumentResponseDto toResponseDto(Document document, DocumentVersion latestVersion) {
        return new DocumentResponseDto(
                document.getId(),
                document.getTitle(),
                document.getCategory(),
                document.getAllowedUsers(),
                document.getTags(),
                latestVersion.getFileUrl(),
                latestVersion.getVersionNumber(),
                latestVersion.getUploadedAt(),
                latestVersion.getUploadedByUserEmail()
        );
    }

    @Override
    public DocumentSummaryDto toSummaryDto(Document document,DocumentVersion latestVersion) {
        Set<String> tagNames = document.getTags() != null
                ? document.getTags().stream().map(Tag::getName).collect(Collectors.toSet())
                : Set.of();

        return new DocumentSummaryDto(
                document.getTitle(),
                document.getCategory(),
                tagNames,
                latestVersion.getFileUrl(),
                latestVersion.getUploadedAt()
        );
    }

    @Override
    public void updateEntity(DocumentUpdateDto dto, Document document) {
        if (dto.getTitle() != null) {
            document.setTitle(dto.getTitle());
        }
        if (dto.getCategory() != null) {
            document.setCategory(dto.getCategory());
        }
        if (dto.getAllowedUsers() != null) {
            document.setAllowedUsers(dto.getAllowedUsers());
        }
        if (dto.getTags() != null) {
            document.setTags(dto.getTags());
        }
    }
}
