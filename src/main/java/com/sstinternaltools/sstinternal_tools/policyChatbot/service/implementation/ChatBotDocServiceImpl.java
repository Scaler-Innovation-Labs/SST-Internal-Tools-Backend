package com.sstinternaltools.sstinternal_tools.policyChatbot.service.implementation;

import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.CloudStorageService;
import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
import com.sstinternaltools.sstinternal_tools.policyChatbot.dtos.ChatBotDocCreateDto;
import com.sstinternaltools.sstinternal_tools.policyChatbot.dtos.ChatBotDocResponseDto;
import com.sstinternaltools.sstinternal_tools.policyChatbot.entity.ChatBotDoc;
import com.sstinternaltools.sstinternal_tools.policyChatbot.mapper.interfaces.ChatBotDocMapper;
import com.sstinternaltools.sstinternal_tools.policyChatbot.repository.ChatBotDocRepository;
import com.sstinternaltools.sstinternal_tools.policyChatbot.service.interfaces.ChatBotDocService;
import jakarta.transaction.Transactional;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ChatBotDocServiceImpl implements ChatBotDocService {

    private final VectorStore vectorStore;
    private final CloudStorageService cloudStorageService;
    private final ChatBotDocRepository chatBotDocRepository;
    private final ChatBotDocMapper chatBotDocMapper;

    public ChatBotDocServiceImpl(VectorStore vectorStore, CloudStorageService cloudStorageService, ChatBotDocRepository chatBotDocRepository, ChatBotDocMapper chatBotDocMapper) {
        this.vectorStore = vectorStore;
        this.cloudStorageService = cloudStorageService;
        this.chatBotDocRepository = chatBotDocRepository;
        this.chatBotDocMapper = chatBotDocMapper;
    }

    @Transactional
    public Long saveDocumentToCloudStorage(ChatBotDocCreateDto createDto){
        String fileUrl=cloudStorageService.uploadFile(createDto.getFile());
        ChatBotDoc chatBotDoc=chatBotDocMapper.fromCreateDto(createDto,fileUrl);
        chatBotDocRepository.save(chatBotDoc);
        return chatBotDoc.getId();
    }

    @Transactional
    @Override
    public void injectDocument(ChatBotDocCreateDto createDto) {

        try {
            Long docId=saveDocumentToCloudStorage(createDto);
            Resource resource = new InputStreamResource(createDto.getFile().getInputStream());
            PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(resource);

            var documents = pdfReader.get();
            if (documents.isEmpty()) {
                throw new ResourceNotFoundException("No text extracted from file");
            }

            TokenTextSplitter textSplitter = new TokenTextSplitter(250, 30, 20, 500, false);
            var chunks = textSplitter.apply(documents);

            System.out.println("Number of chunks created: " + chunks.size());
            if (chunks.isEmpty()) {
                System.err.println("No chunks created from the document content");
                throw new ResourceNotFoundException("No chunks created from the document content");
            }

            // Add metadata to each chunk before saving
            chunks.forEach(doc -> doc.getMetadata().put("docId", docId.toString()));
            vectorStore.accept(chunks);
            System.out.println("VectorStore loaded with PDF content.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error during document ingestion: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteDocumentAndEmbeddings(Long docId) {
        ChatBotDoc doc=chatBotDocRepository.findById(docId).orElseThrow(()->new ResourceNotFoundException("Document not found"));
        String filterExpression = "docId == '" + docId + "'";
        vectorStore.delete(filterExpression);
        cloudStorageService.deleteFile(doc.getFileUrl());
    }

    @Override
    public List<ChatBotDocResponseDto> getAllDocuments() {
        return chatBotDocRepository.findAll().stream().map(chatBotDocMapper::fromEntityToDto).collect(Collectors.toList());
    }

}
