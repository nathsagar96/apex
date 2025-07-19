package com.apex.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.apex.config.NewsApiProperties;
import com.apex.domain.dtos.NewsApiResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class NewsApiClientTest {

  private NewsApiClient newsApiClient;

  @Mock private NewsApiProperties newsApiProperties;
  @Mock private NewsApiProperties.Base base;
  @Mock private NewsApiProperties.Url url;
  @Mock private NewsApiProperties.Content content;
  @Mock private NewsApiProperties.Top top;
  @Mock private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    when(newsApiProperties.getKey()).thenReturn("test-api-key");
    when(newsApiProperties.getBase()).thenReturn(base);
    when(newsApiProperties.getBase().getUrl()).thenReturn("https://test-newsapi.org/v2");
    when(newsApiProperties.getCountry()).thenReturn("us");
    when(newsApiProperties.getUrl()).thenReturn(url);
    when(newsApiProperties.getUrl().getContent()).thenReturn(content);
    when(newsApiProperties.getUrl().getContent().getTop()).thenReturn(top);
    when(newsApiProperties.getUrl().getContent().getTop().getHeadlines())
        .thenReturn("/top-headlines");

    newsApiClient = new NewsApiClient(newsApiProperties, restTemplate);
  }

  @Test
  void getTopHeadlines_shouldReturnNewsApiResponse() {
    // Given
    NewsApiResponse expectedResponse = new NewsApiResponse("ok", 1, List.of());
    String expectedUrl = "https://test-newsapi.org/v2/top-headlines?apiKey=test-api-key&country=us";

    when(restTemplate.getForObject(eq(expectedUrl), eq(NewsApiResponse.class)))
        .thenReturn(expectedResponse);

    // When
    NewsApiResponse actualResponse = newsApiClient.getTopHeadlines();

    // Then
    assertNotNull(actualResponse);
    assertEquals(expectedResponse, actualResponse);
  }
}
