package com.example.javaproject.services.impls;

import com.example.javaproject.dao.repos.ManufacturerRepository;
import com.example.javaproject.dto.manufacturer.ManufacturerDto;
import com.example.javaproject.dto.manufacturer.NewManufacturerModel;
import com.example.javaproject.exceptions.ContractViolationException;
import com.example.javaproject.exceptions.ItemNotFoundException;
import com.example.javaproject.services.mappers.ManufacturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ManufacturerService implements com.example.javaproject.services.interfaces.ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public ManufacturerDto createManufacturer(NewManufacturerModel data) {
        if (Objects.isNull(data))
            throw new ContractViolationException("Data can not be null!");

        if (data.getName().isEmpty())
            throw new ContractViolationException("Name can not be empty");

        return ManufacturerMapper.toDto(
                manufacturerRepository.save(
                        ManufacturerMapper.fromNew(data)
                )
        );
    }

    @Override
    public List<ManufacturerDto> getAll() {
        return manufacturerRepository.findAll().stream().map(ManufacturerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ManufacturerDto> getByCountry(String country) {
        if (Objects.isNull(country))
            throw new ContractViolationException("Country can not be null!");

        return manufacturerRepository.findByCountry(country).stream().map(ManufacturerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ManufacturerDto getById(long id) {
        return ManufacturerMapper.toDto(
                manufacturerRepository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException("Not found"))
        );
    }

    @Override
    public void deleteManufacturer(long id) {
        manufacturerRepository.delete(
                manufacturerRepository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException("Manufacturer not found"))
        );
    }
}
