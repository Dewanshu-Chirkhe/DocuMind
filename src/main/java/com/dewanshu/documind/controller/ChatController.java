package com.dewanshu.documind.controller;

import com.dewanshu.documind.dto.ChatRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return "Question received: " + request.question();
    }
}