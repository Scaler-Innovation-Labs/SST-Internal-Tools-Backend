package com.sstinternaltools.sstinternal_tools.policyChatbot.dtos;

import org.springframework.ai.chat.messages.Message;

import java.util.List;

public class ChatResponse {

    private String conversationId;
    private String answer;

    public ChatResponse(String conversationId, String answer) {
        this.conversationId = conversationId;
        this.answer = answer;
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

}