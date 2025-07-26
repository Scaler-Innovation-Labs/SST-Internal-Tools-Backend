package com.sstinternaltools.sstinternal_tools.Issues.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageService {
    @Value("${ticket.image.upload-dir:uploads}")
    private String uploadDir;

    public String storeImage(MultipartFile file) {
        String extension = getExtension(file.getOriginalFilename());
        String filename = UUID.randomUUID() + "." + extension;
        try {
            Path dirPath = Paths.get(uploadDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            Path filePath = dirPath.resolve(filename);
            file.transferTo(filePath.toFile());
            // Return a URL or path; for now, return relative path
            return "/" + uploadDir + "/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }

    private String getExtension(String filename) {
        if (filename == null) return "";
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }
}

