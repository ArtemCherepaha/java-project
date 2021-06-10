package com.example.javaproject.dao.repos;

import com.example.javaproject.dao.domain.Equipment;
import com.example.javaproject.dao.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByManufacturer(Manufacturer manufacturer);

    @Query(value = "SELECT eq.* " +
            "FROM equipment eq " +
            "JOIN equipment_details details ON eq.details_id = details.id " +
            "WHERE details.has_error = 1",
            nativeQuery = true)
    List<Equipment> findCrashed();

    Optional<Equipment> findBySerialNumber(String serialNumber);

    @Query(value = "SELECT eq.* " +
            "FROM equipment eq " +
            "JOIN equipment_details det ON eq.details_id = det.id " +
            "WHERE TIMESTAMPDIFF(HOUR, det.last_contact_date, NOW()) >= :hour",
            nativeQuery = true)
    List<Equipment> findByContactDate(@Param("hour") int hour);

    @Query(value = "SELECT eq.* " +
            "FROM equipment eq " +
            "JOIN equipment_details det ON det.id = eq.details_id " +
            "WHERE det.current_power < :limit",
            nativeQuery = true)
    List<Equipment> findByPower(@Param("limit") int lowerLimit);

    List<Equipment> findByEquipmentType(String type);
}
