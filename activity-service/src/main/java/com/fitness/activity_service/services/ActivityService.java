package com.fitness.activity_service.services;

import com.fitness.activity_service.dto.ActivityRequest;
import com.fitness.activity_service.dto.ActivityResponse;
import com.fitness.activity_service.models.Activity;
import com.fitness.activity_service.repository.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        Activity activity = Activity.builder()
                .userId(activityRequest.userId())
                .activityType(activityRequest.activityType())
                .duration(activityRequest.duration())
                .caloriesBurned(activityRequest.caloriesBurned())
                .startTime(activityRequest.startTime())
                .additionalMetrics(activityRequest.additionalMetrics())
                .build();


        Activity savedActivity = activityRepository.save(activity);
        return daoToDto(savedActivity);

    }

    private ActivityResponse daoToDto(Activity savedActivity) {
        return new ActivityResponse(
                savedActivity.getId(),
                savedActivity.getUserId(),
                savedActivity.getActivityType(),
                savedActivity.getDuration(),
                savedActivity.getCaloriesBurned(),
                savedActivity.getStartTime(),
                savedActivity.getAdditionalMetrics(),
                savedActivity.getCreatedAt(),
                savedActivity.getUpdatedAt()
        );
    }

    private Activity dtoToDao(ActivityRequest activityRequest, Activity activity) {

        activity.setUserId(activityRequest.userId());
        activity.setActivityType(activityRequest.activityType());
        activity.setDuration(activityRequest.duration());
        activity.setCaloriesBurned(activityRequest.caloriesBurned());
        activity.setStartTime(activityRequest.startTime());
        activity.setAdditionalMetrics(activityRequest.additionalMetrics());
        activity.setCreatedAt(activityRequest.createdAt());
        activity.setUpdatedAt(activityRequest.updatedAt());

        return activity;
    }
}
