package com.example.demo.address.repository;

import com.example.demo.address.repository.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
