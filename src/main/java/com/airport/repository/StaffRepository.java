package com.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airport.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}