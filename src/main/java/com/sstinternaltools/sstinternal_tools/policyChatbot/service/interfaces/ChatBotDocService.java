package com.sstinternaltools.sstinternal_tools.policyChatbot.service.interfaces;

import com.sstinternaltools.sstinternal_tools.policyChatbot.dtos.ChatBotDocCreateDto;

public interface ChatBotDocService {
     void injectDocument(ChatBotDocCreateDto createDto);
     void deleteDocumentAndEmbeddings(Long docId);
}
