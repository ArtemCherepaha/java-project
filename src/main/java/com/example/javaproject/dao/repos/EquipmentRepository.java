package com.example.javaproject.dao.repos;

import com.example.javaproject.dao.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
