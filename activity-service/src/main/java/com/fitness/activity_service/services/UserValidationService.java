package com.fitness.activity_service.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserValidationService {

    private final WebClient userServiceWebClient;

    public UserValidationService(WebClient userServiceWebClient) {
        this.userServiceWebClient = userServiceWebClient;
        log.info("=== UserValidationService initialized with WebClient");
    }

    public Boolean validateUser(String userId){
        try {
            log.info("=== [ACTIVITY-SERVICE] Starting validation for user: {}", userId);
            log.info("=== [ACTIVITY-SERVICE] Calling: http://USERSERVICE/api/users/{}/validate", userId);
            return userServiceWebClient.get().uri("/api/users/{userId}/validate",userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            e.printStackTrace();
        }
        return false;
    }

/*    public Boolean validateUser(String userId) {
        try {
            log.info("=== [ACTIVITY-SERVICE] Starting validation for user: {}", userId);
            log.info("=== [ACTIVITY-SERVICE] Calling: http://USERSERVICE/api/users/{}/validate", userId);

            Boolean result = userServiceWebClient
                    .get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .doOnSubscribe(sub -> log.info("=== [ACTIVITY-SERVICE] Request subscribed"))
                    .doOnSuccess(response -> log.info("=== [ACTIVITY-SERVICE] SUCCESS - Response: {}", response))
                    .doOnError(error -> log.error("=== [ACTIVITY-SERVICE] ERROR - ", error))
                    .onErrorResume(e -> {
                        log.error("=== [ACTIVITY-SERVICE] Error resumed: {}", e.getMessage());
                        return Mono.just(false);
                    })
                    .block();

            log.info("=== [ACTIVITY-SERVICE] Final validation result: {}", result);
            return result != null ? result : false;

        } catch (WebClientResponseException e) {
            log.error("=== [ACTIVITY-SERVICE] WebClientResponseException:");
            log.error("=== Status Code: {}", e.getStatusCode());
            log.error("=== Status Text: {}", e.getStatusText());
            log.error("=== Response Body: {}", e.getResponseBodyAsString());
            log.error("=== Headers: {}", e.getHeaders());
            return false;

        } catch (Exception e) {
            log.error("=== [ACTIVITY-SERVICE] Unexpected error validating user: ", e);
            return false;
        }*/
    }

