package com.apex.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.apex.clients.NewsApiClient;
import com.apex.domain.dtos.Article;
import com.apex.domain.dtos.NewsApiResponse;
import com.apex.domain.dtos.NewsSummaryResponse;
import com.apex.domain.dtos.Source;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;

@ExtendWith(MockitoExtension.class)
class NewsBriefServiceTest {

  private NewsBriefService newsBriefService;

  @Mock private NewsApiClient newsApiClient;

  @Mock private ChatModel chatModel;

  @BeforeEach
  void setUp() {
    newsBriefService = new NewsBriefService(newsApiClient, chatModel);
  }

  @Test
  void generateNewsBrief_shouldReturnNewsSummaryResponse() {
    // Given
    Source source = new Source("test-id", "Test Source");
    Article article =
        new Article(
            source,
            "Author",
            "Test Title",
            "Test Description",
            "http://test.com",
            "http://test.com/image.jpg",
            "2023-01-01T00:00:00Z",
            "Test Content");
    NewsApiResponse newsApiResponse = new NewsApiResponse("ok", 1, List.of(article));

    when(newsApiClient.getTopHeadlines()).thenReturn(newsApiResponse);

    ChatResponse chatResponse =
        new ChatResponse(List.of(new Generation(new AssistantMessage("Test Summary"))));
    when(chatModel.call(any(Prompt.class))).thenReturn(chatResponse);

    // When
    NewsSummaryResponse response = newsBriefService.generateNewsBrief(false);

    // Then
    assertNotNull(response);
    assertNotNull(response.summary());
    assertTrue(response.summary().contains("Test Summary"));
    assertNotNull(response.createdAt());
  }

  @Test
  void generateNewsBrief_renderHtml_shouldReturnHtmlSummaryResponse() {
    // Given
    Source source = new Source("test-id", "Test Source");
    Article article =
        new Article(
            source,
            "Author",
            "Test Title",
            "Test Description",
            "http://test.com",
            "http://test.com/image.jpg",
            "2023-01-01T00:00:00Z",
            "Test Content");
    NewsApiResponse newsApiResponse = new NewsApiResponse("ok", 1, List.of(article));

    when(newsApiClient.getTopHeadlines()).thenReturn(newsApiResponse);

    ChatResponse chatResponse =
        new ChatResponse(
            List.of(
                new Generation(
                    new AssistantMessage("<html><body><h1>Test Summary</h1></body></html>"))));
    when(chatModel.call(any(Prompt.class))).thenReturn(chatResponse);

    // When
    NewsSummaryResponse response = newsBriefService.generateNewsBrief(true);

    // Then
    assertNotNull(response);
    assertNotNull(response.summary());
    assertTrue(response.summary().contains("<html><body><h1>Test Summary</h1></body></html>"));
    assertNotNull(response.createdAt());
  }
}
