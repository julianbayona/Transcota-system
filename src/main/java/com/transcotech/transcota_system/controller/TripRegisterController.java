package com.transcotech.transcota_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.transcotech.transcota_system.Service.TripRegisterService;
import com.transcotech.transcota_system.model.TripRegister;

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

    @GetMapping("/all")
    public List<TripRegister> showAlltri(){
        return tripRegisterService.findAll();
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

    @PostMapping("/create")
    public void createTrip(@RequestBody TripRegister tripRegister) {
        tripRegisterService.createTripRegister(tripRegister);
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
