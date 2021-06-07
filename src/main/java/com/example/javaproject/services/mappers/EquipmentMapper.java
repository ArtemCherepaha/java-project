package com.example.javaproject.services.mappers;

public class EquipmentMapper {
    public static EquipmentDto toDto(Equipment equipment) {
        EquipmentDto dto = new EquipmentDto();

        dto.setId(equipment.getId());
        dto.setSerialNumber(equipment.getSerialNumber());
        dto.setEquipmentType(equipment.getEquipmentType());
        dto.setManufacturerId(equipment.getManufacturer().getId());
        dto.setManufacturerName(equipment.getManufacturer().getName());

        dto.setLastContactDate(equipment.getDetails().getLastContactDate());
        dto.setCurrentPower(equipment.getDetails().getCurrentPower());
        dto.setHasError(equipment.getDetails().hasError());

        return dto;
    }
}
