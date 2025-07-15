package com.sstinternaltools.sstinternal_tools.Issues.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageValidator {
    public void validate(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("File is not an image");
        }
        if (file.getSize() > 5 * 1024 * 1024) { // 5MB limit
            throw new IllegalArgumentException("File size exceeds 5MB");
        }
        // Add more validation if needed (dimensions, etc.)
    }
}

