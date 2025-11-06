package com.fitness.ai_service.service;

import com.fitness.ai_service.dto.RecommendationDto;
import com.fitness.ai_service.model.Recommendation;
import com.fitness.ai_service.repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public List<RecommendationDto> getUserRecommendation(String userId) {

        List<Recommendation> byUserId = recommendationRepository.findByUserId(userId);
        List<RecommendationDto> recommendationDtoList = byUserId.stream().map(this::daoToDto).toList();
        return recommendationDtoList;

    }

    public RecommendationDto getActivityRecommendation(String activityId) {

        Recommendation recommendation = recommendationRepository.findByActivityId(activityId).orElseThrow(() -> new RuntimeException("No Recommendation Found for this Activity." + activityId));
        RecommendationDto recommendationDto = daoToDto(recommendation);
        return recommendationDto;
    }


    private RecommendationDto daoToDto(Recommendation recommendation) {
        return new RecommendationDto(

                recommendation.getId(),
                recommendation.getActivityId(),
                recommendation.getUserId(),
                recommendation.getRecommendation(),
                recommendation.getImprovements(),
                recommendation.getSuggestions(),
                recommendation.getSafety()


        );

    }

    private Recommendation dtoToDao(RecommendationDto recommendationDto, Recommendation recommendation) {
        recommendation.setId(recommendationDto.id());
        recommendation.setActivityId(recommendationDto.activityId());
        recommendation.setUserId(recommendationDto.userId());
        recommendation.setRecommendation(recommendationDto.recommendation());
        recommendation.setImprovements(recommendationDto.safety());
        recommendation.setSuggestions(recommendationDto.suggestions());
        return recommendation;
    }
}
