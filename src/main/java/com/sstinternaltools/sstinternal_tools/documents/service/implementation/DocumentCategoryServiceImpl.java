package com.sstinternaltools.sstinternal_tools.documents.service.implementation;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto.DocumentCategoryCreateDto;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import com.sstinternaltools.sstinternal_tools.documents.mapper.interfaces.DocumentCategoryDtoMapper;
import com.sstinternaltools.sstinternal_tools.documents.repository.DocumentCategoryRepository;
import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.DocumentCategoryService;

public class DocumentCategoryServiceImpl implements DocumentCategoryService {

    private final DocumentCategoryRepository documentCategoryRepository;
    private final DocumentCategoryDtoMapper documentCategoryDtoMapper;

    public DocumentCategoryServiceImpl(DocumentCategoryRepository documentCategoryRepository, DocumentCategoryDtoMapper documentCategoryDtoMapper) {
        this.documentCategoryRepository = documentCategoryRepository;
        this.documentCategoryDtoMapper = documentCategoryDtoMapper;
    }

    public void createDocumentCategory(DocumentCategoryCreateDto documentCategoryCreateDto) {
        DocumentCategory documentCategory =documentCategoryDtoMapper.toEntity(documentCategoryCreateDto);
        documentCategoryRepository.save(documentCategory);
    }


}
