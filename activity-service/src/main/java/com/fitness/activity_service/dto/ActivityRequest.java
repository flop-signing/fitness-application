package com.fitness.activity_service.dto;

import com.fitness.activity_service.utils.ActivityType;

import java.time.LocalDateTime;
import java.util.Map;

public record ActivityRequest(
        String id,
        String userId,
        ActivityType activityType,
        Integer duration,
        Integer caloriesBurned,
        LocalDateTime startTime,
        Map<String, Object> additionalMetrics,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
