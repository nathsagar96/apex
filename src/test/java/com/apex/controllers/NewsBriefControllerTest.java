package com.apex.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.apex.domain.dtos.NewsSummaryResponse;
import com.apex.services.NewsBriefService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class NewsBriefControllerTest {

  private MockMvc mockMvc;

  @Mock private NewsBriefService newsBriefService;

  @BeforeEach
  void setUp() {
    NewsBriefController newsBriefController = new NewsBriefController(newsBriefService);
    mockMvc = MockMvcBuilders.standaloneSetup(newsBriefController).build();
  }

  @Test
  void brief_shouldReturnNewsSummaryResponseAsJson() throws Exception {
    // Given
    NewsSummaryResponse expectedResponse =
        new NewsSummaryResponse(LocalDateTime.now(), "Test Summary JSON");
    when(newsBriefService.generateNewsBrief(false)).thenReturn(expectedResponse);

    // When & Then
    mockMvc
        .perform(get("/api/v1/news/brief").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void briefHtml_shouldReturnNewsSummaryResponseAsHtml() throws Exception {
    // Given
    NewsSummaryResponse expectedResponse =
        new NewsSummaryResponse(
            LocalDateTime.now(), "```html<html><body><h1>Test Summary HTML</h1></body></html>```");
    when(newsBriefService.generateNewsBrief(true)).thenReturn(expectedResponse);

    // When & Then
    mockMvc
        .perform(get("/api/v1/news/brief/render").accept(MediaType.TEXT_HTML_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().string("<html><body><h1>Test Summary HTML</h1></body></html>"));
  }
}
