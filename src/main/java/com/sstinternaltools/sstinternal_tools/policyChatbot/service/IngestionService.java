package com.sstinternaltools.sstinternal_tools.policyChatbot.service;

import com.sstinternaltools.sstinternal_tools.documents.service.interfaces.CloudStorageService;
import com.sstinternaltools.sstinternal_tools.mess.exception.ResourceNotFoundException;
import com.sstinternaltools.sstinternal_tools.policyChatbot.dtos.ChatBotDocCreateDto;
import com.sstinternaltools.sstinternal_tools.policyChatbot.entity.ChatBotDoc;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;



@Service
public class IngestionService{

    private final VectorStore vectorStore;
    private final CloudStorageService cloudStorageService;

    public IngestionService(VectorStore vectorStore, CloudStorageService cloudStorageService){
        this.vectorStore = vectorStore;
        this.cloudStorageService = cloudStorageService;
    }

    public Long saveDocumentToCloudStorage(ChatBotDocCreateDto createDto){
        String fileUrl=cloudStorageService.uploadFile(createDto.getFile());
        ChatBotDoc chatBotDoc=new ChatBotDoc(createDto.getDocumentName(),fileUrl);


    }

    public void injectDocument(ChatBotDocCreateDto createDto) {

        try {

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

            vectorStore.accept(chunks);
            System.out.println("VectorStore loaded with PDF content.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error during document ingestion: " + e.getMessage());
        }
    }

}
