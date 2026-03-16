package com.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.airport.repository.*;
import com.airport.model.*;

@Controller
public class StaffController {

    @Autowired
    private StaffRepository staffRepo;

    @GetMapping("/staff")
    public String staffPage(Model model) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("staffList", staffRepo.findAll());
        return "staff";
    }

    @PostMapping("/save-staff")
    public String saveStaff(@ModelAttribute Staff staff) {
        staffRepo.save(staff);
        return "redirect:/staff";
    }
}