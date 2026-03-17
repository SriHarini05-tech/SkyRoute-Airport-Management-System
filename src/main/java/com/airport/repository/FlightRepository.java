package com.airport.repository;

import com.airport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // ✅ Search by source and destination
    List<Flight> findBySourceAndDestination(String source, String destination);

}