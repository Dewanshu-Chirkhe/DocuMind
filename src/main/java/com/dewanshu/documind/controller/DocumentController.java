package com.dewanshu.documind.controller;

import com.dewanshu.documind.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    private final Path storagePath = Paths.get("storage/documents");

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> upload(
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Files.createDirectories(storagePath);

        Path filePath = storagePath.resolve(file.getOriginalFilename());
        file.transferTo(filePath);

        List<String> chunks = documentService.extractAndSplit(file);

        return ResponseEntity.ok(chunks);
    }
}