package com.example.controller;

import com.example.dto.request.CityRequest;
import com.example.model.City;
import com.example.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;


    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public City addCity(@RequestBody final CityRequest request) {
        return cityService.insert(request);
    }

    @GetMapping("/get/{id}")
    public City getCityById(@PathVariable("id") Long id) {
        return cityService.getCity(id);
    }

    @GetMapping("/all")
    public List<City> getCities() {
        return cityService.getCities();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public City deleteCity(@PathVariable("id") Long id) {
        return cityService.deleteCity(id);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public City editCity(@RequestBody CityRequest request,
                         @PathVariable("id") Long id) {
        return cityService.updatePut(id, request);
    }

}
