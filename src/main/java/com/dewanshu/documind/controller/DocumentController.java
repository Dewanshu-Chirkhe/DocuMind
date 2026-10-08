package com.dewanshu.documind.controller;

import com.dewanshu.documind.service.DocumentService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
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
    private final VectorStore vectorStore;

    private final Path storagePath = Paths.get("storage/documents");

    public DocumentController(
            DocumentService documentService,
            VectorStore vectorStore) {

        this.documentService = documentService;
        this.vectorStore = vectorStore;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("File is empty");
        }

        Files.createDirectories(storagePath);

        Path filePath =
                storagePath.resolve(file.getOriginalFilename());

        file.transferTo(filePath);

        List<Document> documents =
                documentService.extractAndSplit(file);

        vectorStore.add(documents);

        return ResponseEntity.ok(
                "Document uploaded and stored successfully. Chunks: "
                        + documents.size()
        );
    }
}