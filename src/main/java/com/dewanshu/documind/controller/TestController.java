package com.dewanshu.documind.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ChatClient chatClient;

    public TestController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/api/test")
    public String test() {
        return chatClient
                .prompt("Explain RAG in one sentence")
                .call()
                .content();
    }
}