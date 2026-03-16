package com.airport.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airport.model.Flight;
import com.airport.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    private FlightRepository repository;

    public void saveFlight(Flight flight) {
        repository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return repository.findAll();
    }

    public void deleteFlight(Long id) {
        repository.deleteById(id);
    }
}