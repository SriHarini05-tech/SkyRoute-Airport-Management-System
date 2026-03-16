package com.airport.controller;

import com.airport.model.Flight;
import com.airport.repository.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    // show add flight page
    @GetMapping("/add")
    public String addFlight(Model model){

        model.addAttribute("flight", new Flight());

        return "add-flight";
    }

    // save flight
    @PostMapping("/saveFlight")
    public String saveFlight(@ModelAttribute Flight flight){

        flightRepository.save(flight);

        return "redirect:/view";
    }

    // view flights
    @GetMapping("/view")
    public String viewFlights(Model model){

        model.addAttribute("flights", flightRepository.findAll());

        return "view-flights";
    }

    // delete flight
    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id){

        flightRepository.deleteById(id);

        return "redirect:/view";
    }

    // search flight
    @GetMapping("/searchFlight")
    public String searchFlight(@RequestParam String source,
                               @RequestParam String destination,
                               Model model){

        model.addAttribute("flights",
                flightRepository.findBySourceAndDestination(source,destination));

        return "view-flights";
    }

}