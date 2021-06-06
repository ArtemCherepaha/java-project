package com.example.javaproject.dao.repos;

import com.example.javaproject.dao.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> findByCountry(String country);
}
