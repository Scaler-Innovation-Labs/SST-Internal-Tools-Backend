package com.sstinternaltools.sstinternal_tools.policyChatbot.controller;

import com.sstinternaltools.sstinternal_tools.policyChatbot.service.interfaces.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/getAns")
    public String getAns(@RequestParam String message) {
        return chatService.getAns(message);
    }
}
