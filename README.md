# apex: Daily News Brief Service

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Mistral AI](https://img.shields.io/badge/Mistral%20AI-F06C2B?style=for-the-badge&logo=mistralai&logoColor=white)

## Overview

`apex` is a Spring Boot application designed to provide a daily news brief service. It leverages the News API to fetch
top headlines and then uses Spring AI with Mistral AI to summarize these articles into a concise and informative news
brief. The service offers both JSON and HTML renditions of the summary.

## Features

* **Daily News Summarization**: Fetches top global news stories and summarizes them using AI.
* **News API Integration**: Seamlessly integrates with the News API for fetching real-time headlines.
* **Spring AI with Mistral AI**: Utilizes Spring AI for interacting with the Mistral AI model to generate intelligent
  summaries.
* **Caching**: Implements caching for News API responses to improve performance and reduce external API calls.
* **RESTful API**: Provides clear API endpoints for accessing news briefs in JSON and HTML formats.
* **Robust Error Handling**: Includes a global exception handler for consistent error responses.
* **Unit Tests**: Comprehensive test suite ensuring reliability and correctness.

## Technologies Used

* **Spring Boot 3**: Framework for building robust, production-ready Spring applications.
* **Java 21**: The programming language used for the application.
* **Maven**: Dependency management and build automation tool.
* **Spring AI**: Spring's integration with Artificial Intelligence models.
* **Mistral AI**: The Large Language Model (LLM) used for news summarization.
* **Spring Web**: For building RESTful APIs.
* **Spring Validation**: For input validation.
* **Spring Cache**: For caching API responses.
* **JUnit 5 & Mockito**: For unit testing.

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 21 or higher
* Apache Maven 3.6.x or higher
* A News API key (obtain one from [News API](https://newsapi.org/))
* Access to Mistral AI (e.g., via an API key or local setup)

### Cloning the Repository

```bash
git clone https://github.com/nathsagar96/apex.git
cd apex
```

### Configuration

Before running the application, you need to configure your News API key and Mistral AI settings in
`src/main/resources/application.yaml`.

```yaml
# src/main/resources/application.yaml
news:
  api:
    key: YOUR_NEWS_API_KEY # Replace with your actual News API key
    base-url: https://newsapi.org/v2/
    default-country: us
    url-content:
      top:
        headlines: top-headlines

spring:
  ai:
    mistralai:
      chat:
        api-key: YOUR_MISTRAL_AI_API_KEY # Replace with your actual Mistral AI API key
```

### Running the Application

You can run the application using Maven:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## API Endpoints

The service exposes the following RESTful endpoints:

### 1. Get News Brief (JSON)

Retrieves a concise news summary in JSON format.

* **URL**: `/api/v1/news/brief`
* **Method**: `GET`
* **Produces**: `application/json`
* **Response Example**:
  ```json
  {
    "timestamp": "2025-07-18T19:42:03.293Z",
    "summary": "Today's top news includes..."
  }
  ```

### 2. Get News Brief (HTML)

Retrieves a concise news summary rendered as a raw HTML snippet.

* **URL**: `/api/v1/news/brief/render`
* **Method**: `GET`
* **Produces**: `text/html`
* **Response Example**:
  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Daily News Brief</title>
      <style>
          body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; }
          h1 { color: #333; }
          p { color: #555; }
      </style>
  </head>
  <body>
      <h1>Daily News Brief</h1>
      <p>Today's top news includes...</p>
  </body>
  </html>
  ```

## Project Structure

The project follows a feature-driven package organization:

```
com.apex/
├── Application.java          # Main Spring Boot application class
├── clients/                  # External API clients (e.g., NewsApiClient)
├── config/                   # Configuration classes (e.g., CacheConfig, NewsApiProperties)
├── controllers/              # REST controllers (e.g., NewsBriefController)
├── domain/                   # Domain models and DTOs
│   └── dtos/                 # Data Transfer Objects (e.g., Article, NewsSummaryResponse)
├── exceptions/               # Custom exceptions and global exception handler
├── services/                 # Business logic services (e.g., NewsBriefService)
```

## Testing

The project includes comprehensive tests:

* **Unit Tests**: Located in `src/test/java/com/apex/clients`, `src/test/java/com/apex/controllers`, and
  `src/test/java/com/apex/services`. These tests use JUnit 5 and Mockito to verify individual components.

To run all tests:

```bash
mvn test
```

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.