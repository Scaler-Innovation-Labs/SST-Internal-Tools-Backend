package com.sstinternaltools.sstinternal_tools.policyChatbot.service.implementation;

import com.sstinternaltools.sstinternal_tools.policyChatbot.dtos.ChatResponse;
import com.sstinternaltools.sstinternal_tools.policyChatbot.exception.ChatbotServiceException;
import com.sstinternaltools.sstinternal_tools.policyChatbot.exception.InvalidConversationException;
import com.sstinternaltools.sstinternal_tools.policyChatbot.exception.LLMServiceException;
import com.sstinternaltools.sstinternal_tools.policyChatbot.exception.VectorStoreException;
import com.sstinternaltools.sstinternal_tools.policyChatbot.service.interfaces.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
    private final ChatClient chatClient;
    private final PgVectorStore vectorStore;
    private final ChatMemory chatMemory;
//    @Value("${chatbot.max-context-chunks:5}")
//    private int maxContextChunks;
//    @Value("${chatbot.max-conversation-history:20}")
//    private int maxConversationHistory;

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
        try {

            // Input validation
            validateInput(conversationId, message);

            // Get conversation history
            List<Message> historyMessages = getConversationHistory(conversationId);

            // Retrieve relevant context
            String context = retrieveRelevantContext(message);

            // Generate response
            String response = generateResponse(message, historyMessages, context);

            // Update conversation history
            updateConversationHistory(conversationId, message, response);

            return new ChatResponse(conversationId, response, historyMessages);

        } catch (Exception e) {
            logger.error("Error processing chat request for conversation {}: {}", conversationId, e.getMessage(), e);
            throw new ChatbotServiceException("Failed to process chat request", e);
        }
    }

    private String retrieveRelevantContext(String message) {
        try {
            // Retrieve relevant documents from vector store
            List<Document> similarDocs = vectorStore.similaritySearch(message);

            if(similarDocs == null || similarDocs.isEmpty()) {
                logger.info("No relevant documents found for query: {}", message);
                return "No relevant policy documents found for this query.";
            }

            // Filter by similarity threshold and limit chunks
            List<Document> topDocs = similarDocs.stream()
                    .limit(10)
                    .collect(Collectors.toList());

            // Build context with metadata
            return similarDocs.stream()
                    .map(doc -> {
                        String docName = doc.getMetadata().getOrDefault("documentName", "Unknown Document").toString();
                        String page = doc.getMetadata().getOrDefault("page_number", "").toString();
                        String fileUrl = doc.getMetadata().getOrDefault("fileUrl", "").toString();

                        StringBuilder citation = new StringBuilder();
                        citation.append("[").append(docName);
                        if (!page.isEmpty()) citation.append(", Page: ").append(page);
                        if (!fileUrl.isEmpty()) citation.append(", Link: ").append(fileUrl);
                        citation.append("]");

                        return citation.toString() + "\n" + doc.getText();
                    })
                    .collect(Collectors.joining("\n---\n"));

        } catch (Exception e) {
            logger.error("Error retrieving context: {}", e.getMessage(), e);
            throw new VectorStoreException("Failed to retrieve relevant context", e);
        }
    }

    private void validateInput(String conversationId, String message) {
        if (conversationId == null || conversationId.trim().isEmpty()) {
            throw new InvalidConversationException("Conversation ID cannot be null or empty");
        }

        if (message == null || message.trim().isEmpty()) {
            throw new InvalidConversationException("Message cannot be null or empty");
        }

        if (message.length() > 1000) {
            throw new InvalidConversationException("Message too long. Maximum 1000 characters allowed.");
        }
    }

    private List<Message> getConversationHistory(String conversationId) {
        try {
            return chatMemory.get(conversationId);
        } catch (Exception e) {
            logger.warn("Failed to retrieve conversation history for {}: {}", conversationId, e.getMessage());
            return List.of();
        }
    }

    private String generateResponse(String message, List<Message> historyMessages, String context) {
        try {
            // Build optimized chat history
            String chatHistory = buildOptimizedChatHistory(historyMessages);

            // Create enhanced system prompt
            String systemPrompt = createEnhancedSystemPrompt();

            // Build the prompt template
            PromptTemplate promptTemplate = new PromptTemplate("""
{systemPrompt}

Chat History:
{chat_history}

Context:
{context}

User Question: {user_input}

Please provide a clear, accurate, and helpful response based on the provided context and conversation history.
""");

            // Create prompt
            Prompt prompt = promptTemplate.create(Map.of(
                    "systemPrompt", systemPrompt,
                    "chat_history", chatHistory,
                    "context", context,
                    "user_input", message
            ));

            // Call the LLM
            var response = chatClient.prompt(prompt).call().content();

            if (response == null || response.trim().isEmpty()) {
                throw new LLMServiceException("Received empty response from LLM");
            }

            return response;

        } catch (Exception e) {
            logger.error("Error generating response: {}", e.getMessage(), e);
            throw new LLMServiceException("Failed to generate response", e);
        }
    }

    private String buildOptimizedChatHistory(List<Message> historyMessages) {
        if (historyMessages.isEmpty()) {
            return "No previous conversation history.";
        }

        // Take only the last few messages to keep context manageable
        int maxHistoryMessages = Math.min(historyMessages.size(), 10);
        List<Message> recentMessages = historyMessages.subList(
                Math.max(0, historyMessages.size() - maxHistoryMessages),
                historyMessages.size()
        );

        return recentMessages.stream()
                .map(msg -> msg.getMessageType() + ": " + msg.getText())
                .collect(Collectors.joining("\n"));
    }

    private String createEnhancedSystemPrompt() {
        return """
                     You are a helpful assistant for college policy questions. Use only the provided context and conversation history to answer. If you use information from a document, mention the document name, Page number, or file url all in the key value pair if available. If you don't know the answer
                                                                  , say \\\\"I don’t have enough information to answer that question.
                                                                  \\\\" Do not make up answers. If possible, suggest a follow-up question or offer to escalate to a human if the user needs more help.
                                                                  \\\\nAlways cite the source document in your answer if you use it.

                        Example Response:
                
                        The policy states that students must submit leave of absence requests at least two weeks prior to the semester start.
                
                        Source: \s
                        * document_name: "Student Academic Handbook 2025" \s
                        * page_number: 17 \s
                        * url: "https://example.edu/handbook-2025.pdf"
                
       """;
    }

    private void updateConversationHistory(String conversationId, String message, String response) {
        try {
            chatMemory.add(conversationId, List.of(
                    new UserMessage(message),
                    new AssistantMessage(response)
            ));
        } catch (Exception e) {
            logger.warn("Failed to update conversation history for {}: {}", conversationId, e.getMessage());
        }
    }
}

