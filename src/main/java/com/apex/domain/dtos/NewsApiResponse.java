package com.apex.domain.dtos;

import java.util.List;

public record NewsApiResponse(String status, int totalResults, List<Article> articles) {}
