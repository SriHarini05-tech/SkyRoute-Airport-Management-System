package com.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airport.model.Booking;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long>{

    List<Booking> findByUsername(String username);

}