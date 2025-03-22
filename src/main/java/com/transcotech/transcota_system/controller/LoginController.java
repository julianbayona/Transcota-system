package com.transcotech.transcota_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.transcotech.transcota_system.Service.TripRegisterService;
import com.transcotech.transcota_system.dto.TripDTO;

@Controller
public class LoginController {
    @Autowired
    private TripRegisterService tripService;

    @GetMapping("/login")
    public String login(Model model) {
        List<TripDTO> trips = tripService.searchUpcomingFive();
        model.addAttribute("tripList", trips);
        return "login";
    }

    @GetMapping("/")
    public String loginXD(Model model) {
        List<TripDTO> trips = tripService.searchUpcomingFive();
        model.addAttribute("tripList", trips);
        return "index";
    }
}
