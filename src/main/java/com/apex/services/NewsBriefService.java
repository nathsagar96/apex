package com.apex.services;

import com.apex.clients.NewsApiClient;
import com.apex.domain.dtos.Article;
import com.apex.domain.dtos.NewsApiResponse;
import com.apex.domain.dtos.NewsSummaryResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class NewsBriefService {

  private static final Logger log = LoggerFactory.getLogger(NewsBriefService.class);

  private final NewsApiClient newsApiClient;
  private final ChatModel chatModel;

  public NewsBriefService(NewsApiClient newsApiClient, ChatModel chatModel) {
    this.newsApiClient = newsApiClient;
    this.chatModel = chatModel;
  }

  public NewsSummaryResponse generateNewsBrief(boolean isRender) {

    final NewsApiResponse newsApiResponse = newsApiClient.getTopHeadlines();
    log.debug("News API Response: {}", newsApiResponse);

    final String prompt = getPrompt(newsApiResponse.articles(), isRender);
    log.debug("Prompt: {}", prompt);

    ChatResponse response = chatModel.call(new Prompt(prompt));
    log.debug("Mistral Response: {}", response);

    return new NewsSummaryResponse(LocalDateTime.now(), response.getResult().getOutput().getText());
  }

  private String getPrompt(List<Article> articles, boolean isRender) {
    String basePrompt;

    if (isRender) {
      basePrompt =
          """
          You are an expert HTML generator.
          Summarize the following news articles and rerun only the full HTML code for a complete web page.
          Make the page look clean and readable using inline css or basic embedded styles.
          Do not include any explanation, introductions, or markdown formatting - only raw HTML code that can be copies and run in a browser.
          Do not add markdown such as ```html

          """;
    } else {
      basePrompt =
          """
          You are a news summarizer.
          Summarize the top global news stories from today in a concise and informative way.
          Focus on major events, political developments, economic updates, and major technology or science breakthroughs.
          Keep the summary clear, objective, and easy to read, like a daily news brief.

          """;
    }

    StringBuilder articlesContent = new StringBuilder();
    for (Article article : articles) {
      articlesContent.append(
          String.format(
              """
          Title: %s
          Description: %s
          End of article

          """,
              article.title(), article.description()));
    }

    return basePrompt + articlesContent;
  }
}
