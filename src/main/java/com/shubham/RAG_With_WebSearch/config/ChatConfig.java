package com.shubham.RAG_With_WebSearch.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
public class ChatConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, RetrievalAugmentationAdvisor retrievalAugmentationAdvisor){

        return chatClientBuilder
                .defaultAdvisors(List.of(
                        new SimpleLoggerAdvisor(),retrievalAugmentationAdvisor
                ))
                .build();
    }
    @Bean
    DocumentRetriever documentRetriever(VectorStore vectorStore){

        return VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .similarityThreshold(0.5)
                .topK(5)
                .build();
    }
    @Bean
    RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(
            DocumentRetriever documentRetriever,
            @Value("classpath:/promptTemplates/systemTemplate.st") Resource promptTemplate
    )

    {
        PromptTemplate promptTemplate1 = new PromptTemplate(promptTemplate);
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)   // from this implementaion the springAI understand you are using vector
                .queryAugmenter(ContextualQueryAugmenter.builder()
                        .promptTemplate(promptTemplate1)
                        .allowEmptyContext(false)
                        .build())
                .build();
    }


}
