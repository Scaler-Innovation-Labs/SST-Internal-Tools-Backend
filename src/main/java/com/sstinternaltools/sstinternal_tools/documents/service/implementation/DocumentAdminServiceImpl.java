package com.sstinternaltools.sstinternal_tools.documents.service.implementation;

import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentCreateDto;
import com.sstinternaltools.sstinternal_tools.documents.dto.documentDtos.DocumentResponseDto;
import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.DocumentAdminService;
import com.sstinternaltools.sstinternal_tools.security.entity.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class DocumentAdminServiceImpl implements DocumentAdminService {

    public DocumentAdminServiceImpl() {
    }

    public DocumentResponseDto createDocument(DocumentCreateDto createDto){
        String email=((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getEmail();


    }
}
