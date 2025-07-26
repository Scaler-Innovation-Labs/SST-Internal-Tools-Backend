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
        if (file.getSize() > 10 * 1024 * 1024) { // 10MB limit would be ig enought
            throw new IllegalArgumentException("File size exceeds 10MB");
        }
        // would be good to check for specific image formats if needed but for now
        // also inc the size of image if needed in future if heavy images are used

    }
}

