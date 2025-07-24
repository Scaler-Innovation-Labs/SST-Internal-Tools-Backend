package com.sstinternaltools.sstinternal_tools.policyChatbot.service.implementation;

import com.sstinternaltools.sstinternal_tools.policyChatbot.service.interfaces.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;
    private final PgVectorStore vectorStore;
    private final ChatMemory chatMemory;

    public ChatServiceImpl(ChatClient.Builder chatClient,
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


    public String getAns(String message) {
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
