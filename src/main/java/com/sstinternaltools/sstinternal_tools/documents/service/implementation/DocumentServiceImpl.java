package com.sstinternaltools.sstinternal_tools.documents.service.implementation;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentCreateDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentResponseDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentUpdateDto;
import com.sstinternaltools.sstinternal_tools.documents.entity.Document;
import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentVersion;
import com.sstinternaltools.sstinternal_tools.documents.mapper.interfaces.DocumentDtoMapper;
import com.sstinternaltools.sstinternal_tools.documents.mapper.interfaces.DocumentVersionDtoMapper;
import com.sstinternaltools.sstinternal_tools.documents.repository.DocumentRepository;
import com.sstinternaltools.sstinternal_tools.documents.repository.DocumentVersionRepository;
import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.CloudStorageService;
import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.DocumentService;
import com.sstinternaltools.sstinternal_tools.security.entity.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository documentVersionRepository;
    private final DocumentDtoMapper documentDtoMapper;
    private final DocumentVersionDtoMapper documentVersionDtoMapper;
    private final CloudStorageService cloudStorageService;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentVersionRepository documentVersionRepository, DocumentDtoMapper documentDtoMapper, DocumentVersionDtoMapper documentVersionDtoMapper, CloudStorageService cloudStorageService) {
        this.documentRepository = documentRepository;
        this.documentVersionRepository = documentVersionRepository;
        this.documentDtoMapper = documentDtoMapper;
        this.documentVersionDtoMapper = documentVersionDtoMapper;
        this.cloudStorageService = cloudStorageService;
    }

    public void createDocument(DocumentCreateDto createDto){
        Document document=documentDtoMapper.toEntity(createDto);
        documentRepository.save(document);

        createDocumentVersion(document,createDto.getFile(),1L);
    }

    public void updateDocument(DocumentUpdateDto updateDto,Long documentId){
        Document document=documentRepository.getReferenceById(documentId);
        Document updatedDocument=documentDtoMapper.updateEntity(updateDto,document);

        if(updateDto.getFile()!=null){
            DocumentVersion currentDocument=documentVersionRepository.findByDocumentIdAndIsLatestVersionTrue(documentId);
            currentDocument.setLatestVersion(false);
            Long versionNo=documentVersionRepository.findTopByDocumentIdOrderByVersionNumberDesc(documentId).getVersionNumber();
            createDocumentVersion(updatedDocument,updateDto.getFile(),versionNo+1);
        }
    }

    //method to create document version
    public void createDocumentVersion(Document document, MultipartFile multipartFile,Long versionNo){
        String email=((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getEmail();

        String fileUrl=cloudStorageService.uploadFile(multipartFile);

        DocumentVersion documentVersion=documentVersionDtoMapper.fromCreateDto(document,fileUrl,email,versionNo);
        documentVersionRepository.save(documentVersion);

    }

    public void deleteDocument(Long documentId){
        documentVersionRepository.deleteAllByDocumentId(documentId);
        documentRepository.deleteById(documentId);
    }

    public DocumentResponseDto getDocumentById(Long documentId){
        Document document=documentRepository.getReferenceById(documentId);
        DocumentVersion latestVersion=documentVersionRepository.findByDocumentIdAndIsLatestVersionTrue(documentId);
        return documentDtoMapper.toResponseDto(document,latestVersion);
    }
//
//    public List<DocumentResponseDto> getDocumentByCategory(Long categoryId){
//        List<DocumentResponseDto> allDocumentByCategory=
//    }

}
