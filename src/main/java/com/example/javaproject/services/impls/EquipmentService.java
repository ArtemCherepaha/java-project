package com.example.javaproject.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService implements com.example.workequipment.services.interfaces.EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentDetailsRepository equipmentDetailsRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public EquipmentDto getEquipment(long id) {
        return EquipmentMapper.toDto(
                equipmentRepository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException("Equipment not found!"))
        );
    }

    @Override
    public EquipmentDto getEquipment(String serialNumber) {
        return EquipmentMapper.toDto(
                equipmentRepository.findBySerialNumber(serialNumber)
                        .orElseThrow(() -> new ItemNotFoundException("Equipment not found!"))
        );
    }

    @Override
    public List<EquipmentDto> getEquipment() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getCrashed() {
        return equipmentRepository.findCrashed()
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getCrashedByTime(int hour) {
        return equipmentRepository.findByContactDate(hour)
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getCrashedByPower(int power) {
        return equipmentRepository.findByPower(power)
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getEquipmentByType(String type) {
        return equipmentRepository.findByEquipmentType(type)
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateContactDate(UpdateDetailsData data) {
        if (data == null)
            throw new ContractViolationException("Update data can not be null");

        EquipmentDetails details = equipmentDetailsRepository.findByEquipmentId(data.getId())
                .orElseThrow(() -> new ItemNotFoundException("Equipment not found"));

        if (data.getPower() < 0 || data.getPower() > 100)
            throw new ContractViolationException("Power must be in range from 0 to 100");

        details.setLastContactDate(LocalDateTime.now());
        details.setHasError(false);

        equipmentDetailsRepository.save(details);
    }
}
