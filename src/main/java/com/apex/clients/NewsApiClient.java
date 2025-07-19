package com.apex.clients;

import com.apex.config.NewsApiProperties;
import com.apex.domain.dtos.NewsApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NewsApiClient {

  private static final Logger log = LoggerFactory.getLogger(NewsApiClient.class);

  private final NewsApiProperties newsApiProperties;
  private final RestTemplate restTemplate;

  public NewsApiClient(NewsApiProperties newsApiProperties, RestTemplate restTemplate) {
    this.newsApiProperties = newsApiProperties;
    this.restTemplate = restTemplate;
  }

  @Cacheable("newsApiResponses")
  public NewsApiResponse getTopHeadlines() {
    final String url = getTopHeadlinesUrl();

    final NewsApiResponse newsApiResponse = restTemplate.getForObject(url, NewsApiResponse.class);
    log.debug("Top Headlines Response: {}", newsApiResponse);

    return newsApiResponse;
  }

  private String getTopHeadlinesUrl() {

    String url =
        UriComponentsBuilder.fromHttpUrl(
                newsApiProperties.getBase().getUrl() + newsApiProperties.getUrl().getContent().getTop().getHeadlines())
            .queryParam("apiKey", newsApiProperties.getKey())
            .queryParam("country", newsApiProperties.getCountry())
            .toUriString();

    log.debug("Top Headlines URL: {}", url);

    return url;
  }
}
