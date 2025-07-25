package com.sstinternaltools.sstinternal_tools.policyChatbot.dtos;

import org.springframework.ai.chat.messages.Message;

import java.util.List;

public class ChatResponse {

    private String conversationId;
    private String answer;
    private List<Message> history;

    public ChatResponse(String conversationId, String answer, List<Message> history) {
        this.conversationId = conversationId;
        this.answer = answer;
        this.history = history;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Message> getHistory() {
        return history;
    }

    public void setHistory(List<Message> history) {
        this.history = history;
    }
}