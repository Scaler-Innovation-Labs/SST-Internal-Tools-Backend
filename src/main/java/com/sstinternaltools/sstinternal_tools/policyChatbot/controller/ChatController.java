package com.sstinternaltools.sstinternal_tools.policyChatbot.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final PgVectorStore vectorStore;
    private final ChatMemory chatMemory;

    public ChatController(ChatClient.Builder chatClient,
                          PgVectorStore vectorStore,
                          JdbcChatMemoryRepository chatMemoryRepository) {

        this.chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository) // ✅ persist to DB
                .maxMessages(30)
                .build();

        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build(),new QuestionAnswerAdvisor(vectorStore))
                .build();

        this.vectorStore = vectorStore;
    }

    @PostMapping("/getAns")
    public String getAns(@RequestParam String message) {
        return chatClient
                .prompt()
                .advisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .user(message)
                .call()
                .content();
    }
}
