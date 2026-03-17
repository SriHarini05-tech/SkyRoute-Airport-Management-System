package com.airport.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String redirectDashboard(Authentication auth){

        String role = auth.getAuthorities().iterator().next().getAuthority();

        if(role.equals("ROLE_ADMIN")){
            return "redirect:/adminDashboard";
        }
        else if(role.equals("ROLE_STAFF")){
            return "redirect:/staffDashboard";
        }
        else{
            return "redirect:/userDashboard";
        }
    }
}