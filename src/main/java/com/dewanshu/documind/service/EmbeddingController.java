package com.dewanshu.documind.controller;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmbeddingController {

    private final EmbeddingModel embeddingModel;

    public EmbeddingController(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @GetMapping("/api/embedding")
    public String testEmbedding() {

        float[] embedding =
                embeddingModel.embed("This is a test document for DocuMind.");

        return "Embedding generated successfully. Dimensions: "
                + embedding.length;
    }
}