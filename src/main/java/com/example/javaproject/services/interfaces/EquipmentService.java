package com.example.javaproject.services.interfaces;

import com.example.javaproject.dto.equipment.EquipmentDto;
import com.example.javaproject.dto.equipment.NewEquipmentModel;
import com.example.javaproject.dto.equipment.UpdateDetailsData;

import java.util.List;

public interface EquipmentService {
    EquipmentDto getEquipment(long id);
    EquipmentDto getEquipment(String serialNumber);
    List<EquipmentDto> getEquipment();

    List<EquipmentDto> getCrashed();

    List<EquipmentDto> getCrashedByTime(int hour);

    List<EquipmentDto> getCrashedByPower(int power);

    List<EquipmentDto> getEquipmentByType(String type);

    void updateContactDate(UpdateDetailsData id);

    EquipmentDto create(NewEquipmentModel model);

    List<EquipmentDto> getByManufacturer(Long manufacturerId);

    void deleteEquipment(long id);
}
