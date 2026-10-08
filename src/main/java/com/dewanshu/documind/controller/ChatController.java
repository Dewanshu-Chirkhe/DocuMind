package com.dewanshu.documind.controller;

import com.dewanshu.documind.dto.ChatRequest;
import com.dewanshu.documind.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return chatService.answer(request.question());
    }
}
