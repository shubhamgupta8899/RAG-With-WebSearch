package com.shubham.RAG_With_WebSearch.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private ChatClient chatClient;
    private VectorStore vectorStore;


    public RagController(ChatClient chatClient, VectorStore vectorStore){

        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @Value("classpath:/promptTemplates/ragTemplate.st")
    Resource promptTemplate;
    @Value("classpath:/promptTemplates/systemTemplate.st")
    Resource systemTemplate;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String question){

        String ans = chatClient.prompt()

                .user(question)
                .call()
                .content();
        return ResponseEntity.ok(ans);
    }

    @GetMapping("/chat/pdf")
    public ResponseEntity<String> chatPdf(@RequestParam String question){


        String ans = chatClient.prompt()
                .user(question)
                .call()
                .content();

        return ResponseEntity.ok(ans);
    }


    // To check if document is null or not
    private String documentContent(Document document){

        String content = document.getFormattedContent();
        if(content == null || content.isBlank()){

            content = document.getText();
        }

        return content;

    }


}
