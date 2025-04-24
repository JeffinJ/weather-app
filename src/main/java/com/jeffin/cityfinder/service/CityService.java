package com.jeffin.cityfinder.service;

import com.jeffin.cityfinder.dto.CityResponse;

public interface CityService {
    /**
     * Find all cities that start with the specified letter
     *
     * @param letter The letter to search for
     * @return CityResponse containing count and matching cities
     */
    CityResponse findCitiesByStartingLetter(String letter);

    /**
     * Get all available cities
     *
     * @return CityResponse containing all cities and their count
     */
    CityResponse getAllCities();
}