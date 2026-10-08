package com.dewanshu.documind.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final Path storagePath = Paths.get("storage/documents");

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file)
            throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        Files.createDirectories(storagePath);

        Path filePath = storagePath.resolve(file.getOriginalFilename());
        file.transferTo(filePath);

        return ResponseEntity.ok(
                "File uploaded successfully: " + file.getOriginalFilename()
        );
    }
}