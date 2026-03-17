package com.airport.controller;

import com.airport.model.*;
import com.airport.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/book")
public String bookFlight(Model model){

    model.addAttribute("booking", new Booking());
    model.addAttribute("flights", flightRepository.findAll());

    return "book-flight";
}
@GetMapping("/searchFlights")
public String searchFlights(@RequestParam String source,
                           @RequestParam String destination,
                           Model model){

    List<Flight> flights =
            flightRepository.findBySourceAndDestination(source, destination);

    model.addAttribute("booking", new Booking());
    model.addAttribute("flights", flights);

    return "book-flight";
}

    @PostMapping("/saveBooking")
public String saveBooking(@ModelAttribute Booking booking,
                          Authentication auth,
                          Model model){

    // 🔍 check flightId
    if(booking.getFlightId() == null){
        model.addAttribute("error","Please select a flight");
        model.addAttribute("flights", flightRepository.findAll());
        return "book-flight";
    }

    Flight flight = flightRepository.findById(booking.getFlightId()).orElse(null);

    if(flight == null){
        model.addAttribute("error","Invalid flight selected");
        model.addAttribute("flights", flightRepository.findAll());
        return "book-flight";
    }

    if(booking.getTickets() <= 0){
        model.addAttribute("error","Invalid ticket count");
        model.addAttribute("flights", flightRepository.findAll());
        return "book-flight";
    }

    if(flight.getRemainingSeats() < booking.getTickets()){
        model.addAttribute("error","Not enough seats available");
        model.addAttribute("flights", flightRepository.findAll());
        return "book-flight";
    }

    // seat allocation
    int startSeat = flight.getTotalSeats() - flight.getRemainingSeats() + 1;

    String seats = "";
    for(int i=0;i<booking.getTickets();i++){
        seats += (startSeat + i);
        if(i < booking.getTickets()-1) seats += ",";
    }

    booking.setSeatNumbers(seats);
    booking.setUsername(auth.getName());
    booking.setTravelDate(flight.getTravelDate());
    booking.setTotalPrice(booking.getTickets() * flight.getTicketPrice());
    booking.setFlight(flight);

    flight.setRemainingSeats(flight.getRemainingSeats() - booking.getTickets());

    flightRepository.save(flight);
    bookingRepository.save(booking);

    model.addAttribute("booking", booking);

    return "ticket";
}

    @GetMapping("/viewBookings")
    public String viewBookings(Model model, Authentication auth){

        String role = auth.getAuthorities().toString();

        if(role.contains("ADMIN") || role.contains("STAFF")){
            model.addAttribute("bookings", bookingRepository.findAll());
        }
        else{
            model.addAttribute("bookings",
                    bookingRepository.findByUsername(auth.getName()));
        }

        return "view-bookings";
    }
}