package com.fitness.ai_service.dto;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

public record RecommendationDto(
        String id,
        String activityId,
        String userId,
        String recommendation,
        List<String> improvements,
        List<String> suggestions,
        List<String> safety
) {
}
