package com.sstinternaltools.sstinternal_tools.policyChatbot.service.implementation;

import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
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

        if(similarDocs == null || similarDocs.isEmpty()) {
            throw new ResourceNotFoundException("I dont have information about this question");
        }

        List<Document> topDocs = similarDocs.stream().limit(5).collect(Collectors.toList());
        String context = topDocs.stream()
                .map(doc -> {
                    String docName = doc.getMetadata().getOrDefault("documentName", "Unknown Document").toString();
                    String fileUrl = doc.getMetadata().getOrDefault("fileUrl", "").toString();
                    String page=doc.getMetadata().getOrDefault("page_number", "").toString();
                    String citation = "[" + docName+ (page.isEmpty() ? "" : ", Page: " + page) + (fileUrl.isEmpty() ? "" : ", Link: " + fileUrl) + "]";
                    return citation + "\n" + doc.getText();
                }).collect(Collectors.joining("\n---\n"));

        System.out.println(context);

        // Defined system prompt to guide the assistant
        String systemPrompt = """
              You are a helpful assistant for college policy questions. Use only the provided context and conversation history to answer. If you use information from a document, mention the document name, Page number, or file url all in the key value pair if available. If you don't know the answer
              , say \\"I don’t have enough information to answer that question.
              \\" Do not make up answers. If possible, suggest a follow-up question or offer to escalate to a human if the user needs more help.
              \\nAlways cite the source document in your answer if you use it.
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

