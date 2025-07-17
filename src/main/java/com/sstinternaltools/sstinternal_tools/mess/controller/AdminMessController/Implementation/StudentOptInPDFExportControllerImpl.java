package com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Implementation;

import com.sstinternaltools.sstinternal_tools.mess.controller.AdminMessController.Interface.StudentOptInPDFExportController;
import com.sstinternaltools.sstinternal_tools.mess.entity.Hostel;
import com.sstinternaltools.sstinternal_tools.mess.service.InternalServices.Interface.StudentOptInPDFExportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT_ADMIN', 'SUPER_ADMIN')")
@RequestMapping("/mess/admin")
public class StudentOptInPDFExportControllerImpl implements StudentOptInPDFExportController {

    private final StudentOptInPDFExportService studentOptInPDFExportService;

    public StudentOptInPDFExportControllerImpl(StudentOptInPDFExportService studentOptInPDFExportService) {
        this.studentOptInPDFExportService = studentOptInPDFExportService;
    }

    @Override
    @GetMapping("/downloadStudentOptInPDF/{hostel}")
    public ResponseEntity<InputStreamResource> downloadStudentOptInPDF(@PathVariable Hostel hostel) {
        try {
            ByteArrayInputStream bis = studentOptInPDFExportService.generateStudentOptInPDF(hostel);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=studentOptInList.pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
