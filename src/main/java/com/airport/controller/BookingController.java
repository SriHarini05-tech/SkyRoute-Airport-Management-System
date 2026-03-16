package com.airport.controller;

import com.airport.model.*;
import com.airport.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/book")
    public String bookFlight(Model model){

        model.addAttribute("booking", new Booking());
        model.addAttribute("flights", flightRepository.findAll());

        return "book-flight";
    }

    @PostMapping("/saveBooking")
public String saveBooking(@ModelAttribute Booking booking,
                          Authentication auth,
                          Model model){

    booking.setUsername(auth.getName());

    bookingRepository.save(booking);

    model.addAttribute("booking", booking);

    return "ticket";
}

    @GetMapping("/viewBookings")
    public String viewBookings(Model model,
                               Authentication auth){

        String role = auth.getAuthorities().toString();

        if(role.contains("ADMIN") || role.contains("STAFF")){

            model.addAttribute("bookings",
                    bookingRepository.findAll());

        } else {

            model.addAttribute("bookings",
                    bookingRepository.findByUsername(auth.getName()));

        }

        return "view-bookings";
    }
}