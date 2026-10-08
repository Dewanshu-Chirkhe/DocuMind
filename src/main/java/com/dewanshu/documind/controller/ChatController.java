package com.dewanshu.documind.controller;

import com.dewanshu.documind.dto.ChatRequest;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final VectorStore vectorStore;

    public ChatController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public List<String> chat(@RequestBody ChatRequest request) {

        List<Document> documents =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query(request.question())
                                .topK(3)
                                .build()
                );

        return documents.stream()
                .map(Document::getText)
                .toList();
    }
}