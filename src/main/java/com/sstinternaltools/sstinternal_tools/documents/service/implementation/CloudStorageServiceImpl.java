package com.sstinternaltools.sstinternal_tools.documents.service.implementation;

import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.CloudStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class CloudStorageServiceImpl implements CloudStorageService {

    @Value("${supabase.storage.bucket}")
    private String bucket;

    @Value("${supabase.project.url}")
    private String supabaseUrl;

    @Value("${supabase.api.key}")
    private String supabaseKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + fileName;

            var headers = new org.springframework.http.HttpHeaders();
            headers.set("Authorization", "Bearer " + supabaseKey);
            headers.set("Content-Type", file.getContentType());

            var request = new org.springframework.http.HttpEntity<>(file.getBytes(), headers);

            // Perform PUT request to upload the file
            restTemplate.put(uploadUrl, request);

            // Return the public URL to access the file
            return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload to Supabase", e);
        }
    }
}
