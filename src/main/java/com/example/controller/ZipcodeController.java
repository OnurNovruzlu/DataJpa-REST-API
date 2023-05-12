package com.example.controller;

import com.example.dto.request.ZipCodeRequest;
import com.example.model.ZipCode;
import com.example.service.ZipCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
@RequiredArgsConstructor
public class ZipcodeController {

    private final ZipCodeService zipcodeService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ZipCode addZipcode(@RequestBody ZipCodeRequest request) {
        return zipcodeService.insertZipCode(request);
    }

    @GetMapping("/get/{id}")
    public ZipCode getZipcode(@PathVariable("id") Long id) {
        return zipcodeService.getZipCode(id);
    }

    @GetMapping("/all")
    public List<ZipCode> getZipcodes() {
        return zipcodeService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ZipCode deleteZipcode(@PathVariable("id") Long id) {
        return zipcodeService.deleteZipCode(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ZipCode editZipcode(@RequestBody ZipCodeRequest request,
                               @PathVariable("id") Long id) {
        return zipcodeService.updatePut(id, request);
    }

    @PostMapping("/addCity/{cityId}/toZipcode/{zipcodeId}")
    @ResponseStatus(HttpStatus.OK)
    public ZipCode addCity(@PathVariable("cityId") Long cityId,
                           @PathVariable("zipcodeId") Long zipcodeId) {
        return zipcodeService.addCityToZipCode(zipcodeId, cityId);
    }

    @PostMapping("/removeCity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ZipCode deleteCity(@PathVariable("id") Long id) {
        return zipcodeService.removeCityFromZipCode(id);
    }
}
