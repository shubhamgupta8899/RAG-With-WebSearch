# RAG With Web Search

A Retrieval-Augmented Generation (RAG) application built using Spring AI, Ollama, Qdrant, and Tavily Search.

This project combines the power of:

*  Document Retrieval (Vector Database)
*  Real-Time Web Search
*  Local LLMs using Ollama
*  Semantic Search with Qdrant
*  Spring AI Retrieval Augmentation Advisor

The application can answer questions using both indexed documents and live web data, making responses more accurate and up-to-date.

---

#  Features

### Document-Based RAG

* Store documents in Qdrant Vector Database.
* Generate embeddings using Ollama.
* Retrieve relevant chunks using semantic search.

### Web Search Integration

* Search the internet using Tavily Search API.
* Convert search results into Spring AI Documents.
* Inject live web context into LLM prompts.

### Retrieval Augmentation

* Uses Spring AI RetrievalAugmentationAdvisor.
* Automatically augments user queries with retrieved context.
* Prevents hallucinations by grounding responses in real data.

### Local AI Models

* Runs completely locally using Ollama.
* No dependency on external LLM providers.

---

#  Tech Stack

| Technology     | Purpose                |
| -------------- | ---------------------- |
| Java 21        | Backend Development    |
| Spring Boot 4  | Application Framework  |
| Spring AI      | AI Integration         |
| Ollama         | Local LLM & Embeddings |
| Qdrant         | Vector Database        |
| Tavily API     | Web Search             |
| Maven          | Dependency Management  |
| Docker Compose | Container Management   |

---

#  Project Structure

```text
src
│
├── config
│   └── ChatConfig.java
│
├── controller
│   ├── RagController.java
│   └── WebSearchRetriever.java
│
├── promptTemplates
│   ├── ragTemplate.st
│   └── systemTemplate.st
│
└── resources
    └── application.properties
```

---

#  Prerequisites

Before running the project make sure you have:

### Java

```bash
Java 21+
```

### Maven

```bash
Maven 3.9+
```

### Docker

```bash
Docker Desktop
```

### Ollama

Install Ollama:

```bash
https://ollama.com
```

Pull required models:

```bash
ollama pull llama3
ollama pull mxbai-embed-large
```

---

#  Start Qdrant

Run Qdrant using Docker:

```bash
docker run -p 6333:6333 -p 6334:6334 qdrant/qdrant
```

Verify:

```bash
http://localhost:6333/dashboard
```

---

#  Tavily API Setup

Get your API key from:

https://tavily.com

Add it in:

```properties
tavily.api-key=YOUR_API_KEY
```

---

#  Application Configuration

```properties
spring.application.name=RAG-With-WebSearch

logging.level.org.springframework.ai.chat.client.advisor=debug

spring.ai.vectorstore.qdrant.host=localhost
spring.ai.vectorstore.qdrant.port=6334
spring.ai.vectorstore.qdrant.collection-name=rag-with-web
spring.ai.vectorstore.qdrant.initialize-schema=true
spring.ai.vectorstore.qdrant.use-tls=false

spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.embedding.model=mxbai-embed-large
spring.ai.ollama.chat.model=llama3

tavily.api-key=YOUR_API_KEY
tavily.result-limit=5
```

---

#  How It Works

## User Question

```text
Who won the latest IPL final?
```

↓

## Tavily Search

Searches the internet for relevant information.

↓

## Convert Search Results

Results are transformed into Spring AI Documents.

↓

## Retrieval Augmentation

Documents are added as context.

↓

## Ollama LLM

Generates a grounded answer.

↓

## Final Response

```text
Kolkata Knight Riders won the IPL final...
```

---

#  Retrieval Flow

```text
User Query
     │
     ▼
WebSearchRetriever
     │
     ▼
Tavily Search API
     │
     ▼
Spring AI Documents
     │
     ▼
RetrievalAugmentationAdvisor
     │
     ▼
Ollama (Llama3)
     │
     ▼
Generated Response
```

---

#  Running the Application

Clone repository:

```bash
git clone https://github.com/your-username/RAG-With-WebSearch.git
```

Move into project:

```bash
cd RAG-With-WebSearch
```

Run:

```bash
mvn spring-boot:run
```

---

#  API Endpoints

## Chat With Web Search

### Request

```http
GET /api/rag/chat/web?question=What is Spring AI?
```

### Example

```http
http://localhost:8080/api/rag/chat/web?question=What is Spring AI?
```

### Response

```json
Spring AI is a framework that simplifies AI integration into Spring applications...
```

---

#  Key Components

## ChatConfig

Responsible for:

* ChatClient configuration
* RetrievalAugmentationAdvisor setup
* Logging advisor
* RestClient creation

---

## WebSearchRetriever

Responsible for:

* Calling Tavily API
* Parsing search results
* Creating Spring AI Documents
* Providing retrieval context

---

## RagController

Responsible for:

* Receiving user questions
* Calling ChatClient
* Returning generated responses

---

#  Future Improvements

* Hybrid Search (Vector + Keyword)
* Chat Memory
* Source Citations
* Streaming Responses
* Multi-Document Upload
* PDF Processing
* Reranking
* Agentic Workflows
* MCP Integration
* Multi-LLM Support

---

#  Learning Outcomes

This project demonstrates:

* Retrieval-Augmented Generation (RAG)
* Spring AI Advisors
* Semantic Search
* Vector Databases
* Embedding Models
* Local LLM Deployment
* Real-Time Web Search Integration

---

#  Author

**Shubham Gupta**

Passionate about:

* Generative AI
* Spring Boot
* Vector Databases
* RAG Systems
* AI Agents
* Scalable Backend Development


Happy Coding! 🚀
