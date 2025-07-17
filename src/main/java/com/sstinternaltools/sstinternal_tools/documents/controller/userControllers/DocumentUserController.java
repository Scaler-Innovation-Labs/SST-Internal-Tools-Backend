package com.sstinternaltools.sstinternal_tools.documents.controller.userControllers;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentCreateDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentResponseDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentUpdateDto;
import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.DocumentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/document/user")
public class DocumentUserController {

    private final DocumentService documentService;

    public DocumentUserController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Get document by ID (with access check)
    @GetMapping("getById/{id}")
    public ResponseEntity<DocumentResponseDto> getDocumentById(@PathVariable Long id) {
        DocumentResponseDto responseDto = documentService.getDocumentById(id);
        return ResponseEntity.ok(responseDto);
    }

    // Get documents by category (with filtering by access)
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<DocumentResponseDto>> getDocumentsByCategoryId(@PathVariable Long categoryId) {
        List<DocumentResponseDto> results = documentService.getDocumentByCategoryId(categoryId);
        return ResponseEntity.ok(results);
    }
}
