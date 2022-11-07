package com.example.demo.address.repository;

import com.example.demo.address.repository.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
