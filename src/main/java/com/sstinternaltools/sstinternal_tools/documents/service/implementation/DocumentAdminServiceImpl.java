package com.sstinternaltools.sstinternal_tools.documents.service.implementation;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentCreateDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentResponseDto;
import com.sstinternaltools.sstinternal_tools.documents.entity.Document;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentVersion;
import com.sstinternaltools.sstinternal_tools.documents.mapper.interfaces.DocumentDtoMapper;
import com.sstinternaltools.sstinternal_tools.documents.mapper.interfaces.DocumentVersionDtoMapper;
import com.sstinternaltools.sstinternal_tools.documents.repository.DocumentRepository;
import com.sstinternaltools.sstinternal_tools.documents.repository.DocumentVersionRepository;
import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.CloudStorageService;
import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.DocumentAdminService;
import com.sstinternaltools.sstinternal_tools.security.entity.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class DocumentAdminServiceImpl implements DocumentAdminService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository documentVersionRepository;
    private final DocumentDtoMapper documentDtoMapper;
    private final DocumentVersionDtoMapper documentVersionDtoMapper;
    private final CloudStorageService cloudStorageService;

    public DocumentAdminServiceImpl(DocumentRepository documentRepository, DocumentVersionRepository documentVersionRepository,DocumentDtoMapper documentDtoMapper,DocumentVersionDtoMapper documentVersionDtoMapper, CloudStorageService cloudStorageService) {
        this.documentRepository = documentRepository;
        this.documentVersionRepository = documentVersionRepository;
        this.documentDtoMapper = documentDtoMapper;
        this.documentVersionDtoMapper = documentVersionDtoMapper;
        this.cloudStorageService = cloudStorageService;
    }

    public void createDocument(DocumentCreateDto createDto){

        String email=((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getEmail();
        Document document=documentDtoMapper.toEntity(createDto);
        documentRepository.save(document);

        String fileUrl=cloudStorageService.uploadFile(createDto.getFile());

        DocumentVersion documentVersion=documentVersionDtoMapper.fromCreateDto(createDto,document,fileUrl,email);
        documentVersionRepository.save(documentVersion);
    }
}
