package com.sstinternaltools.sstinternal_tools.policyChatbot.service.implementation;

import com.sstinternaltools.sstinternal_tools.policyChatbot.dtos.ChatResponse;
import com.sstinternaltools.sstinternal_tools.policyChatbot.service.interfaces.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;
    private final PgVectorStore vectorStore;
    private final ChatMemory chatMemory;

    public ChatServiceImpl(ChatClient.Builder chatClientBuilder,
                           PgVectorStore vectorStore,
                           JdbcChatMemoryRepository chatMemoryRepository) {

        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;

        // Initialize chat memory once to persist conversation history in Postgres.
        this.chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(30) // adjust window size as needed
                .build();
    }

    @Override
    public ChatResponse getAns(String conversationId, String message) {

        final String finalConversationId = conversationId;

        // Get existing conversation history messages from chatMemory (Postgres)
        List<Message> historyMessages = chatMemory.get(finalConversationId);

        // Build chat history string suitable for prompt
        String chatHistory = historyMessages.stream()
                .map(msg -> msg.getMessageType() + ": " + msg.getText())
                .collect(Collectors.joining("\n"));

        // Retrieve relevant documents from vector store to build context
        List<Document> similarDocs = vectorStore.similaritySearch(message);
        String context = similarDocs.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));

        System.out.println(context);

        // Definedr system prompt to guide the assistant
        String systemPrompt = """
               You are an expert assistant helping answer user questions based on provided context from documents, as well as prior conversation history with the user. Remember personal information shared by the user during this conversation and use it in answers.
                                            If the answer is not found in the documents or conversation history, say: "I don’t have enough information to answer that question."
        """;

        // Build the prompt template with placeholders
        PromptTemplate promptTemplate = new PromptTemplate("""
                {systemPrompt}

                Chat History:
                {chat_history}

                Context:
                {context}

                User:
                {user_input}
                """);


        Prompt prompt = promptTemplate.create(Map.of(
                "systemPrompt", systemPrompt,
                "chat_history", chatHistory,
                "context", context,
                "user_input", message
        ));


        System.out.println("========= PROMPT TO LLM =========");
        System.out.println(prompt.toString());
        System.out.println("===============================");

        // Call the LLM via Spring AI ChatClient
        var response = chatClient.prompt(prompt).call().content();
        System.out.println(response);

        // Persist the user message and assistant response to chat memory
        chatMemory.add(finalConversationId, List.of(
                new UserMessage(message),
                new AssistantMessage(response)
        ));

        // Return conversation ID, LLM response, and full conversation history
        return new ChatResponse(finalConversationId, response, historyMessages);
    }
}

