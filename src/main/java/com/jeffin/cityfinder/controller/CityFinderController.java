package com.jeffin.cityfinder.controller;

import com.jeffin.cityfinder.dto.CityResponse;
import com.jeffin.cityfinder.service.CityService;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
public class CityFinderController {

    private static final Logger logger = LoggerFactory.getLogger(CityFinderController.class);
    private final CityService cityService;

    @Autowired
    public CityFinderController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/by-letter/{letter}")
    public ResponseEntity<CityResponse> getCitiesByLetter(
            @PathVariable
            @Pattern(regexp = "^[a-zA-Z]$", message = "Letter must be a single alphabetic character")
            String letter) {

        logger.info("Received request to find cities starting with letter: {}", letter);

        CityResponse response = cityService.findCitiesByStartingLetter(letter);

        logger.info("Found {} cities starting with letter: {}", response.getCount(), letter);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<CityResponse> getAllCities() {
        logger.info("Received request for all cities");

        CityResponse response = cityService.getAllCities();

        logger.info("Returning all {} cities", response.getCount());
        return ResponseEntity.ok(response);
    }

}
