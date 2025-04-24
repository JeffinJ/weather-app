package com.jeffin.cityfinder.service;

import com.jeffin.cityfinder.client.WeatherApiClient;
import com.jeffin.cityfinder.dto.CityResponse;
import com.jeffin.cityfinder.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    private final WeatherApiClient weatherApiClient;

    @Autowired
    public CityServiceImpl(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    @Override
    @Cacheable(value = "citiesByLetter", key = "#letter.toLowerCase()")
    public CityResponse findCitiesByStartingLetter(String letter) {
        logger.debug("Finding cities starting with letter: {}", letter);

        if (letter == null || letter.isEmpty()) {
            return new CityResponse(0, Collections.emptyList());
        }

        List<City> allCities = weatherApiClient.fetchCities();

        String letterLowerCase = letter.toLowerCase();

        List<String> matchingCities = allCities.stream()
                .filter(city -> city.getName() != null && !city.getName().isEmpty())
                .map(City::getName)
                .filter(name -> name.toLowerCase().startsWith(letterLowerCase))
                .collect(Collectors.toList());

        return new CityResponse(matchingCities.size(), matchingCities);
    }

    @Override
    @Cacheable(value = "allCities")
    public CityResponse getAllCities() {
        logger.debug("Fetching all cities");

        List<City> allCities = weatherApiClient.fetchCities();

        List<String> cityNames = allCities.stream()
                .filter(city -> city.getName() != null && !city.getName().isEmpty())
                .map(City::getName)
                .collect(Collectors.toList());

//        List<City> validCities = allCities.stream()
//                .filter(city -> city.getName() != null && !city.getName().isEmpty())
//                .toList();


        return new CityResponse(cityNames.size(), cityNames);
    }

    @Scheduled(fixedRateString = "${cache.evict.interval:3600000}")
    public void evictCaches() {
        logger.info("Evicting caches to refresh city data");
        // This would use CacheManager to clear caches if configured
    }
}
