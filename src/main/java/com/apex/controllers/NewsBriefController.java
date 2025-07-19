package com.apex.controllers;

import com.apex.domain.dtos.NewsSummaryResponse;
import com.apex.services.NewsBriefService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/news/")
public class NewsBriefController {

  private final NewsBriefService newsBriefService;

  public NewsBriefController(NewsBriefService newsBriefService) {
    this.newsBriefService = newsBriefService;
  }

  @GetMapping(value = "/brief", produces = MediaType.APPLICATION_JSON_VALUE)
  public NewsSummaryResponse brief() {
    return newsBriefService.generateNewsBrief(false);
  }

  @GetMapping(value = "/brief/render", produces = MediaType.TEXT_HTML_VALUE)
  public String briefHtml() {
    NewsSummaryResponse newsSummaryResponse = newsBriefService.generateNewsBrief(true);
    return newsSummaryResponse.summary().substring(7, newsSummaryResponse.summary().length() - 3);
  }
}
