package com.example.demo.address.repository;

import com.example.demo.address.repository.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
