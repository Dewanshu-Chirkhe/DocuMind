package com.dewanshu.documind.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

    public List<String> extractAndSplit(MultipartFile file) throws IOException {

        String text;

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(document);
        }

        TokenTextSplitter splitter = TokenTextSplitter.builder().build();

        List<Document> documents =
                splitter.apply(List.of(new Document(text)));

        return documents.stream()
                .map(Document::getText)
                .toList();
    }
}