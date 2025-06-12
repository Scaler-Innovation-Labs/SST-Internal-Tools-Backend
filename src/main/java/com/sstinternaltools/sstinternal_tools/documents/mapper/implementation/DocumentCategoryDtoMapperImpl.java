package com.sstinternaltools.sstinternal_tools.documents.mapper.implementation;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto.DocumentCategoryCreateDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto.DocumentCategoryResponseDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto.DocumentCategorySummaryDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto.DocumentCategoryUpdateDto;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import com.sstinternaltools.sstinternal_tools.documents.mapper.interfaces.DocumentCategoryDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentCategoryDtoMapperImpl implements DocumentCategoryDtoMapper {

    @Override
    public DocumentCategory toEntity(DocumentCategoryCreateDto dto) {
        return new DocumentCategory(dto.getName(), dto.getDescription());
    }

    @Override
    public DocumentCategoryResponseDto toResponseDto(DocumentCategory category) {
        return new DocumentCategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    @Override
    public DocumentCategorySummaryDto toSummaryDto(DocumentCategory category) {
        return new DocumentCategorySummaryDto(
                category.getName(),
                category.getDescription()
        );
    }

    @Override
    public void updateEntity(DocumentCategoryUpdateDto dto, DocumentCategory category) {
        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
    }
}
