package com.airport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard(){
        return "admin-dashboard";
    }

    @GetMapping("/staffDashboard")
    public String staffDashboard(){
        return "staff-dashboard";
    }

    @GetMapping("/userDashboard")
    public String userDashboard(){
        return "user-dashboard";
    }
}