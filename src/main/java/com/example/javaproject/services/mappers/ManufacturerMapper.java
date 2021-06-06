package com.example.javaproject.services.mappers;

import com.example.javaproject.dao.domain.Manufacturer;
import com.example.javaproject.dto.manufacturer.ManufacturerDto;
import com.example.javaproject.dto.manufacturer.NewManufacturerModel;

public class ManufacturerMapper {
    public static ManufacturerDto toDto(Manufacturer manufacturer){
        ManufacturerDto dto = new ManufacturerDto();

        dto.setId(manufacturer.getId());
        dto.setName(manufacturer.getName());
        dto.setCountry(manufacturer.getCountry());
        dto.setContactNumber(manufacturer.getContactNumber());

        return dto;
    }

    public static Manufacturer fromNew(NewManufacturerModel model){
        Manufacturer manufacturer = new Manufacturer();

        manufacturer.setName(model.getName());
        manufacturer.setCountry(model.getCountry());
        manufacturer.setContactNumber(model.getContactNumber());

        return manufacturer;
    }
}
