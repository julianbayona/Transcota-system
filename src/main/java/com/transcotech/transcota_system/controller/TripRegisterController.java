package com.transcotech.transcota_system.controller;

import com.transcotech.transcota_system.Service.TripRegisterService;
import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.model.TripRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trips")
public class TripRegisterController {

    @Autowired
    private TripRegisterService tripRegisterService;

    @GetMapping
    public String showAllTrips(Model model){
        model.addAttribute("drivers", tripRegisterService.findAll());
        return "select_trip";
    }

    @GetMapping("/search")
    public String searchTrip(@RequestParam("tripId") Long tripId, Model model) {
        var trip = tripRegisterService.searchTripRegisterById(tripId);
        if (trip != null) {
            model.addAttribute("trip", trip);
        } else {
            model.addAttribute("error", "No se encontró el viaje con ID: " + tripId);
        }
        return "select_trip";
    }

    @PostMapping("/register")
    public String registerTrip(@ModelAttribute("trip") TripRegister tripRegister, RedirectAttributes redirectAttributes) {
        try {
            tripRegisterService.createTripRegister(tripRegister); // Guarda el viaje en la BD
            redirectAttributes.addFlashAttribute("success", "Viaje registrado con éxito!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el viaje.");
        }
        return "redirect:/trips/register"; // Redirige después de guardar
    }



}
