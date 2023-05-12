package com.example.service;

import com.example.dto.request.CityRequest;
import com.example.model.City;
import com.example.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{
    private final CityRepository repository;

    @Override
    public City insert(CityRequest request) {
        City city = new City();
        city.setName(request.getName());
        return repository.save(city);
    }

    @Override
    public List<City> getCities() {
        return repository.findAll();
    }

    @Override
    public City getCity(Long id) {
        return repository.findById(id).orElseThrow(()->
                new IllegalArgumentException("city by cityId: "+id+" could not be found"));
    }

    @Override
    public City deleteCity(Long id) {
        City city = getCity(id);
        repository.delete(city);
        return city;
    }

    @Transactional
    @Override
    public City updatePut(Long id, CityRequest request) {
        City city = getCity(id);
        city.setName(request.getName());
        return city;
    }
}
