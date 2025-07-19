package com.apex.domain.dtos;

import java.time.LocalDateTime;

public record NewsSummaryResponse(LocalDateTime createdAt, String summary) {}
