package com.sstinternaltools.sstinternal_tools.documents.dto;

import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

public class DocumentCreateDto {

    private String title;
    private String code;
    private DocumentCategory category;
    private Set<String> tags;
    private Set<String> userAllowed;
    private MultipartFile file;

}
