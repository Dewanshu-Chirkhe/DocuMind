package com.dewanshu.documind.controller;

import com.dewanshu.documind.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    public ChatController(
            VectorStore vectorStore,
            ChatClient.Builder chatClientBuilder) {

        this.vectorStore = vectorStore;
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {

        List<Document> documents =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query(request.question())
                                .topK(3)
                                .build()
                );

        String context = documents.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + "\n\n" + b);

        String prompt = """
                Answer the question using only the provided context.

                If the answer cannot be found in the context, say:
                "I don't know based on the provided documents."

                Context:
                %s

                Question:
                %s
                """.formatted(context, request.question());

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}