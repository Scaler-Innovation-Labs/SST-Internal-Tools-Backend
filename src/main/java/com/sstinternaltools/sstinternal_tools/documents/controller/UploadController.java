package com.sstinternaltools.sstinternal_tools.documents.controller;

import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.CloudStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload-test")
public class UploadController {

    private  CloudStorageService cloudStorageService;

    public UploadController(CloudStorageService cloudStorageService) {
        this.cloudStorageService = cloudStorageService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String uploadedUrl = cloudStorageService.uploadFile(file);
        return ResponseEntity.ok("File uploaded to: " + uploadedUrl);
    }
}
