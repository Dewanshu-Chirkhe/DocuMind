package com.dewanshu.documind.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.vectorstore.SearchRequest;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final VectorStore vectorStore;

    public SearchController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping
    public List<String> search(@RequestParam String query) {

        List<Document> documents =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query(query)
                                .topK(3)
                                .build()
                );

        return documents.stream()
                .map(Document::getText)
                .toList();
    }
}