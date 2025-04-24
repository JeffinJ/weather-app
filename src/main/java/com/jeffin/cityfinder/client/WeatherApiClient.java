package com.jeffin.cityfinder.client;

import com.jeffin.cityfinder.exception.ExternalApiException;
import com.jeffin.cityfinder.model.City;
import com.jeffin.cityfinder.model.WeatherApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class WeatherApiClient {

    private static final Logger logger = LoggerFactory.getLogger(WeatherApiClient.class);

    private final RestTemplate restTemplate;
    private final String apiUrl;

    @Autowired
    public WeatherApiClient(
            RestTemplate restTemplate,
            @Value("${weather.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Cacheable(value = "cityData")
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 100)
    )
    public List<City> fetchCities() {
        try {
            logger.debug("🟦 Fetching cities from external API: {}", apiUrl);

            ResponseEntity<WeatherApiResponse> response =
                    restTemplate.getForEntity(apiUrl, WeatherApiResponse.class);

            if (response.getBody() == null || response.getBody().getList() == null) {
                logger.warn("Received empty response from weather API");
                return Collections.emptyList();
            }

            logger.debug("Successfully fetched {} cities from API", response.getBody().getList().size());
            return response.getBody().getList();

        } catch (RestClientException e) {
            logger.error("Failed to fetch cities from external API", e);
            throw new ExternalApiException("Failed to fetch cities from weather API", e);
        }
    }
}