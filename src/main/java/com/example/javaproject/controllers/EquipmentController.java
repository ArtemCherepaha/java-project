package com.example.javaproject.controllers;

import com.example.javaproject.dto.equipment.EquipmentDto;
import com.example.javaproject.dto.equipment.NewEquipmentModel;
import com.example.javaproject.dto.equipment.UpdateDetailsData;
import com.example.javaproject.services.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;

@RestController
@RequestMapping("equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getEquipment(){
        return equipmentService.getEquipment();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentDto getEquipment(@PathVariable long id){
        return equipmentService.getEquipment(id);
    }

    @GetMapping("serial/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentDto getEquipment(@PathVariable String serialNumber){
        return equipmentService.getEquipment(serialNumber);
    }

    @GetMapping("type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getEquipmentByType(@PathVariable String type){
        return equipmentService.getEquipmentByType(type);
    }
    @PostMapping("update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@RequestBody UpdateDetailsData data){
        equipmentService.updateContactDate(data);
    }

    @PostMapping
    public EquipmentDto create(@RequestBody NewEquipmentModel model){
        return equipmentService.create(model);
    }

    @GetMapping("manufacturer/{manufacturerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getByManufacturer(@PathVariable Long manufacturerId){
        return equipmentService.getByManufacturer(manufacturerId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        equipmentService.deleteEquipment(id);
    }
}

