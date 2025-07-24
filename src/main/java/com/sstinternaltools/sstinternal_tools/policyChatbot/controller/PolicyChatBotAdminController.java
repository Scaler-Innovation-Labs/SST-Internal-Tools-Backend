package com.sstinternaltools.sstinternal_tools.policyChatbot.controller;

import com.sstinternaltools.sstinternal_tools.policyChatbot.dtos.ChatBotDocCreateDto;
import com.sstinternaltools.sstinternal_tools.policyChatbot.service.implementation.ChatBotDocServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("policyChatBot/admin")
public class PolicyChatBotAdminController {

    private final ChatBotDocServiceImpl ingestionService;

    public PolicyChatBotAdminController(ChatBotDocServiceImpl ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute ChatBotDocCreateDto chatBotDocCreateDto) {
        ingestionService.injectDocument(chatBotDocCreateDto);
        return ResponseEntity.ok("Document uploaded successfully");
    }



}
