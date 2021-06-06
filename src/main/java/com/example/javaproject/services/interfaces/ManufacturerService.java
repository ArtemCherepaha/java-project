package com.example.javaproject.services.interfaces;

import com.example.javaproject.dto.manufacturer.ManufacturerDto;
import com.example.javaproject.dto.manufacturer.NewManufacturerModel;

import java.util.List;

public interface ManufacturerService {
    ManufacturerDto createManufacturer(NewManufacturerModel data);
    List<ManufacturerDto> getAll();
    List<ManufacturerDto> getByCountry(String country);
    ManufacturerDto getById(long id);
    void deleteManufacturer(long id);
}
