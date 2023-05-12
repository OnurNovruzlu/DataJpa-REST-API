package com.example.service;

import com.example.dto.request.ZipCodeRequest;
import com.example.model.City;
import com.example.model.ZipCode;
import com.example.repository.ZipCodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ZipCodeServiceImpl implements ZipCodeService {
    private final ZipCodeRepository repository;
    private final CityService cityService;

    @Override
    public List<ZipCode> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public ZipCode insertZipCode(ZipCodeRequest request) {
        ZipCode code = new ZipCode();
        code.setName(request.getName());
        if(request.getCityId()==null){
            return repository.save(code);
        }
        City city = cityService.getCity(request.getCityId());
        code.setCity(city);
        return repository.save(code);
    }

    @Override
    public ZipCode deleteZipCode(Long zipCodeId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        repository.delete(zipCode);
        return zipCode;
    }

    @Override
    public ZipCode getZipCode(Long zipCodeId) {
        return repository.findById(zipCodeId).orElseThrow(()->
                new IllegalArgumentException("Zipcode with zipcodeId: "+zipCodeId+" could not be found"));
    }

    @Transactional
    @Override
    public ZipCode updatePut(Long cityId, ZipCodeRequest request) {
        ZipCode zipCode = getZipCode(cityId);
        zipCode.setName(request.getName());
        if(request.getCityId()==null){
            return zipCode;
        }
        City city = cityService.getCity(request.getCityId());
        zipCode.setCity(city);
        return zipCode;
    }

    @Transactional
    @Override
    public ZipCode addCityToZipCode(Long zipcodeId, Long cityId) {
        ZipCode zipCode = getZipCode(zipcodeId);
        City city = cityService.getCity(cityId);
        if(Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("zipcode has already exists");
        }
        zipCode.setCity(city);
        return zipCode;
    }

    @Transactional
    @Override
    public ZipCode removeCityFromZipCode(Long zipcodeId) {
        ZipCode zipCode = getZipCode(zipcodeId);
        if(!Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("zipcode does not have a city");
        }
        zipCode.setCity(null);
        return zipCode;
    }
}