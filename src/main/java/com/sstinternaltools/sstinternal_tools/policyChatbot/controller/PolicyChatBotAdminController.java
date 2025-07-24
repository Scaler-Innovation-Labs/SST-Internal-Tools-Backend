package com.sstinternaltools.sstinternal_tools.policyChatbot.controller;

import com.sstinternaltools.sstinternal_tools.policyChatbot.service.IngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("policyChatBot/admin")
public class PolicyChatBotAdminController {

    private final IngestionService ingestionService;

    public PolicyChatBotAdminController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        ingestionService.injectDocument(file);
        return ResponseEntity.ok("Document uploaded successfully");
    }



}
