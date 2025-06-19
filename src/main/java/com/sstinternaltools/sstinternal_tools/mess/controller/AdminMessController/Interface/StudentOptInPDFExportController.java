package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface StudentOptInPDFExportController {
    ResponseEntity<InputStreamResource> downloadStudentOptInPDF();
}
