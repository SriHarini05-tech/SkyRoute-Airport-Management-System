package com.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airport.model.Flight;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findBySourceAndDestination(String source,String destination);

}