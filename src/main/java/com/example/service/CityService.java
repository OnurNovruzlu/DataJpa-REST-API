package com.example.service;

import com.example.dto.request.CityRequest;
import com.example.model.City;

import java.util.List;

public interface CityService {
    City insert(CityRequest request);
    List<City> getCities();
    City getCity(Long id);
    City deleteCity(Long id);
    City updatePut(Long id,CityRequest request);
}
