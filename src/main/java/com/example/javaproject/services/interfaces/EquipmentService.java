package com.example.javaproject.services.interfaces;

public interface EquipmentService {
    EquipmentDto getEquipment(long id);
    EquipmentDto getEquipment(String serialNumber);
    List<EquipmentDto> getEquipment();

    List<EquipmentDto> getCrashed();

    List<EquipmentDto> getCrashedByTime(int hour);

    List<EquipmentDto> getCrashedByPower(int power);

    List<EquipmentDto> getEquipmentByType(String type);
}
