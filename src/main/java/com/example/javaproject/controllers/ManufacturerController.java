package com.example.javaproject.controllers;

import com.example.javaproject.dto.manufacturer.ManufacturerDto;
import com.example.javaproject.dto.manufacturer.NewManufacturerModel;
import com.example.javaproject.services.interfaces.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manufacturers")
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ManufacturerDto> getAll(){
        return manufacturerService.getAll();
    }

    @GetMapping("country/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<ManufacturerDto> getByCountry(@PathVariable String name){
        return manufacturerService.getByCountry(name);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ManufacturerDto getById(@PathVariable Long id){
        return manufacturerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturerDto create(@RequestBody NewManufacturerModel data){
        return manufacturerService.createManufacturer(data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        manufacturerService.deleteManufacturer(id);
    }
}
