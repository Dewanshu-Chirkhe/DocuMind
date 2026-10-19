<div align="center">

# DocuMind

### Chat with your documents.

A document Q&A chatbot that allows users to upload PDF files and ask questions about their content. Built using Spring Boot, Spring AI, Google Gemini, and PGVector to retrieve relevant information and generate context-aware answers.

<p>
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-Backend-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_AI-RAG-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-PGVector-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/Google_Gemini-LLM-8E75B2?style=for-the-badge&logo=googlegemini&logoColor=white" />
</p>

</div>

---

## About the Project

DocuMind is a document question-answering chatbot built using Retrieval-Augmented Generation (RAG).

It allows users to upload PDF documents and ask questions based on their content. Instead of sending the entire document to an AI model, the application retrieves relevant sections and uses them as context to generate answers.

The project explores how Spring AI can be used to integrate language models, embedding models, and vector databases into a Spring Boot application.

## Features

- **PDF Upload:** Upload PDF documents for processing.
- **Text Extraction:** Extract text from PDF files using Apache PDFBox.
- **Text Chunking:** Split extracted text into smaller chunks for efficient retrieval.
- **Embedding Generation:** Generate vector embeddings for document chunks using Spring AI.
- **Vector Storage:** Store embeddings and associated metadata in PostgreSQL using PGVector.
- **Semantic Search:** Retrieve relevant document chunks based on the meaning of a question.
- **AI-Powered Answers:** Use Google Gemini to generate answers based on retrieved document content.
- **Context-Based Responses:** Provide answers grounded in the information retrieved from uploaded documents.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot | Backend application and REST APIs |
| Spring AI | Integration with AI models and vector stores |
| Google Gemini | Language model for answer generation |
| PostgreSQL | Relational database |
| PGVector | Vector storage and similarity search |
| Apache PDFBox | PDF text extraction |
| Maven | Dependency management |

## How It Works

DocuMind follows a Retrieval-Augmented Generation (RAG) pipeline.

### 1. Document Upload

The user uploads a PDF document through the application. The Spring Boot backend receives the file and processes it.

### 2. Text Extraction

Apache PDFBox extracts the text from the uploaded PDF.

### 3. Text Chunking

The extracted text is divided into smaller chunks using Spring AI's text-splitting utilities. This makes it easier to retrieve relevant portions of the document.

### 4. Embedding Generation

Spring AI generates vector embeddings for the document chunks using the configured embedding model.

Embeddings represent the semantic meaning of text as numerical vectors, allowing similar pieces of text to be identified.

### 5. Vector Storage

The generated embeddings, along with the corresponding text and metadata, are stored in PostgreSQL using PGVector.

PGVector enables similarity searches to find document chunks relevant to a user's question.

### 6. Question Processing

When the user asks a question, the application searches the vector store for document chunks that are semantically similar to the question.

### 7. Answer Generation

The retrieved chunks are passed to Google Gemini along with the user's question.

Gemini uses the retrieved information as context to generate a relevant answer.

## Architecture

```text
             PDF Document
                   |
                   v
          Spring Boot Backend
                   |
                   v
             Apache PDFBox
                   |
                   v
              Text Chunking
                   |
                   v
          Spring AI Embeddings
                   |
                   v
           PostgreSQL + PGVector
                   |
                   v
             User Question
                   |
                   v
            Similarity Search
                   |
                   v
           Relevant Text Chunks
                   |
                   v
             Google Gemini
                   |
                   v
             Generated Answer
```

## Getting Started

Follow these steps to run DocuMind locally.

### Prerequisites

Make sure you have the following installed:

- Java 21
- Maven
- PostgreSQL
- A Google Gemini API key

### 1. Clone the Repository

```bash
git clone https://github.com/Dewanshu-Chirkhe/DocuMind.git
cd DocuMind
```

### 2. Configure PostgreSQL

Create a PostgreSQL database for the application and ensure the PGVector extension is available.

```sql
CREATE DATABASE documind;
```

Connect to the database and enable the extension if it is not already enabled:

```sql
CREATE EXTENSION IF NOT EXISTS vector;
```

### 3. Configure Environment Variables

Configure the required database credentials and Gemini API key using environment variables.

For example:

```env
GEMINI_API_KEY=your_gemini_api_key
DB_URL=jdbc:postgresql://localhost:5432/documind
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password
```

Use the variable names expected by your application's configuration. Do not commit API keys or database passwords to GitHub.

### 4. Run the Application

Using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

The application will start using the configured server port.

## What I Learned

Working on DocuMind helped me explore:

- Integrating Spring AI into a Spring Boot application.
- Understanding Retrieval-Augmented Generation (RAG).
- Working with embedding models and vector databases.
- Implementing semantic similarity search using PGVector.
- Processing PDF documents and preparing text for retrieval.
- Integrating Google Gemini into an AI-powered backend.
- Building a complete document retrieval and question-answering pipeline.

## Future Improvements

- Support for additional document formats.
- Improved chunking and retrieval strategies.
- Conversation history.
- Retrieval from multiple documents.
- Improved handling of questions that cannot be answered from the available context.

---

<div align="center">

**DocuMind** — Making document interaction simpler with RAG.

Built with Java, Spring Boot, Spring AI, and PGVector.

</div>
