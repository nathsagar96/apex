# apex: Daily News Brief Service 🚀

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-21-blue.svg?logo=openjdk&logoColor=white)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green.svg?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.x-red.svg?logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Spring AI](https://img.shields.io/badge/Spring_AI-1.x-blue.svg?logo=spring&logoColor=white)](https://spring.io/projects/spring-ai)
[![Mistral AI](https://img.shields.io/badge/Mistral_AI-x.x-orange.svg?logo=spring&logoColor=white)](https://mistral.ai/)

## Project Overview ✨

`apex` is a robust and intuitive daily news brief service built with Spring Boot. It intelligently fetches top headlines
from the News API and leverages Spring AI with Mistral AI to summarize these articles into a concise and informative
news brief. The service is designed with a focus on clean architecture, security, and performance, offering both JSON
and HTML renditions of the summarized news. `apex` aims to deliver a seamless experience for staying updated with daily
global events.

## Key Features 🚀

* **Daily News Summarization**: Fetches top global news stories and summarizes them using AI.
* **News API Integration**: Seamlessly integrates with the News API for fetching real-time headlines.
* **Spring AI with Mistral AI**: Utilizes Spring AI for interacting with the Mistral AI model to generate intelligent
  summaries.
* **Caching**: Implements caching for News API responses to improve performance and reduce external API calls.
* **RESTful API**: Provides clear API endpoints for accessing news briefs in JSON and HTML formats.
* **Robust Error Handling**: Includes a global exception handler for consistent error responses.
* **Unit Tests**: Comprehensive test suite ensuring reliability and correctness.

## Technologies Used 🛠️

* **Spring Boot 3**: Framework for building robust, production-ready Spring applications.
* **Java 21**: The programming language used for the application.
* **Maven**: Dependency management and build automation tool.
* **Spring AI**: Spring's integration with Artificial Intelligence models.
* **Mistral AI**: The Large Language Model (LLM) used for news summarization.
* **Spring Web**: For building RESTful APIs.
* **Spring Validation**: For input validation.
* **Spring Cache**: For caching API responses.
* **JUnit 5 & Mockito**: For unit testing.

## Getting Started 🏁

Follow these instructions to get a copy of the project up and running on your local machine for development and testing
purposes.

### Prerequisites 📋

Before you begin, ensure you have the following installed:

* **Java Development Kit (JDK) 21** or higher
* **Apache Maven 3.6.x** or higher
* **Git**
* A **News API key** (obtain one from [News API](https://newsapi.org/))
* Access to **Mistral AI** (e.g., via an API key or local setup)

### Cloning the Repository ⚙️

1. **Clone the repository:**
   ```bash
   git clone https://github.com/nathsagar96/apex.git
   cd apex
   ```

### Configuration 🛠️

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

### Running the Application ▶️

You can run the application using Maven:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## API Endpoints 📖

The service exposes the following RESTful endpoints. For interactive documentation, please refer to the Swagger UI.

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

## Project Structure 📂

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

## Testing 🧪

The project includes comprehensive tests:

* **Unit Tests**: Located in `src/test/java/com/apex/clients`, `src/test/java/com/apex/controllers`, and
  `src/test/java/com/apex/services`. These tests use JUnit 5 and Mockito to verify individual components.

To run all tests:

```bash
mvn test
```

## Contributing 🤝

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also
simply open an issue with the tag "enhancement".
Don't forget to give the project a star! ⭐ Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License 📄

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.