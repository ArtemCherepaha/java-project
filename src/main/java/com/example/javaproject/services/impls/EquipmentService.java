package com.example.javaproject.services.impls;

import com.example.javaproject.dao.domain.Equipment;
import com.example.javaproject.dao.domain.EquipmentDetails;
import com.example.javaproject.dao.domain.Manufacturer;
import com.example.javaproject.dao.repos.EquipmentDetailsRepository;
import com.example.javaproject.dao.repos.EquipmentRepository;
import com.example.javaproject.dao.repos.ManufacturerRepository;
import com.example.javaproject.dto.equipment.EquipmentDto;
import com.example.javaproject.dto.equipment.NewEquipmentModel;
import com.example.javaproject.dto.equipment.UpdateDetailsData;
import com.example.javaproject.exceptions.ContractViolationException;
import com.example.javaproject.exceptions.ItemNotFoundException;
import com.example.javaproject.services.mappers.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService implements com.example.javaproject.services.interfaces.EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentDetailsRepository equipmentDetailsRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public EquipmentService() {
    }

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

    @Override
    public EquipmentDto create(NewEquipmentModel model) {
        if (model == null)
            throw new ContractViolationException("Model can not be null");

        if (model.getSerialNumber()==null)
            throw new ContractViolationException("Serial number can not be null");

        if (model.getPower() < 1)
            throw new ContractViolationException("Volume can not be less than 1");

        if (model.getEquipmentType().length() < 1)
            throw new ContractViolationException("Please, specify equipment type");

        if (model.getPower() < 0 || model.getPower() > 100)
            throw new ContractViolationException("Power must be in range from 0 to 100");

        Manufacturer manufacturer = manufacturerRepository.findById(model.getManufacturerId()).orElseThrow(() -> new ItemNotFoundException("Manufacturer not found!"));

        EquipmentDetails details = new EquipmentDetails();
        details.setHasError(false);
        details.setLastContactDate(LocalDateTime.now());
        details.setCurrentPower(model.getPower());


        Equipment equipment = new Equipment();

        equipment.setSerialNumber(model.getSerialNumber());
        equipment.setEquipmentType(model.getEquipmentType());
        equipment.setManufacturer(manufacturer);
        equipment.setDetails(equipmentDetailsRepository.save(details));

        return EquipmentMapper.toDto(equipmentRepository.save(equipment));
    }

    @Override
    public List<EquipmentDto> getByManufacturer(Long manufacturerId) {
        if (manufacturerId == null)
            throw new ContractViolationException("Id can not be null!");

        return equipmentRepository.findByManufacturer(
                manufacturerRepository.findById(manufacturerId)
                        .orElseThrow(() -> new ItemNotFoundException("Manufacturer not found!"))
        ).stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEquipment(long id) {
        equipmentRepository.delete(
                equipmentRepository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException("Equipment not found"))
        );
    }
}
