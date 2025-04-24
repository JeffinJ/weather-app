package com.jeffin.cityfinder.dto;

import java.util.List;

public class CityResponse {
    private int count;
    private List<String> cities;

    public CityResponse(int count, List<String> cities) {
        this.count = count;
        this.cities = cities;
    }

    public int getCount() {
        return count;
    }

    public List<String> getCities() {
        return cities;
    }
}
