package com.example.service;

import com.example.dto.request.ZipCodeRequest;
import com.example.model.ZipCode;

import java.util.List;

public interface ZipCodeService{
    List<ZipCode> getAll();
    ZipCode insertZipCode(ZipCodeRequest request);
    ZipCode deleteZipCode(Long zipCodeId);
    ZipCode getZipCode(Long zipCodeId);
    ZipCode updatePut(Long cityId, ZipCodeRequest request);
    ZipCode addCityToZipCode(Long zipcodeId,Long cityId);
    ZipCode removeCityFromZipCode(Long zipcodeId);
}
