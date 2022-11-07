package com.example.demo.address.repository;

import com.example.demo.address.repository.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
