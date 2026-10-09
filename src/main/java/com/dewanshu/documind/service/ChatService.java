package com.dewanshu.documind.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    public ChatService(
            VectorStore vectorStore,
            ChatClient.Builder chatClientBuilder) {

        this.vectorStore = vectorStore;
        this.chatClient = chatClientBuilder.build();
    }

    public String answer(String question) {

        List<Document> documents =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query(question)
                                .topK(3)
                                .build()
                );

        String context = documents.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + "\n\n" + b);

        String prompt = """
                You are DocuMind, a document question-answering assistant.
                
                        Answer the user's question using the provided document context.
                
                        Follow these rules:
                        1. Answer directly without introductory phrases.
                        2. Do not start with phrases like:
                           - "Based on the provided context"
                           - "According to the provided document"
                           - "From the given information"
                        3. Use clear, natural language.
                        4. Use headings and bullet points only when they improve readability.
                        5. Do not repeat the question in your answer.
                        6. Do not invent information that is absent from the context.
                        7. If the answer cannot be found in the documents, say:
                           "I couldn't find this information in the provided documents."
                
                        Return only the detailed answer. Do not include unnecessary introductions."

                Context:
                %s

                Question:
                %s
                """.formatted(context, question);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
