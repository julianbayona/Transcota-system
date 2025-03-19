package com.transcotech.transcota_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transcotech.transcota_system.Service.TripRegisterService;
import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.model.TripVehicleDTO;

@Controller
@RequestMapping("/trips")
public class TripRegisterController {

    @Autowired
    private TripRegisterService tripRegisterService;

    /*@GetMapping
    public String showAllTrips(Model model){
        model.addAttribute("drivers", tripRegisterService.findAll());
        return "select_trip";
    }*/

    @GetMapping("/all")
    public List<TripDTO> showAllTrips(){
        return tripRegisterService.findAll();
    }

    @GetMapping("/select")
    public String showSearchForm(Model model) {
        model.addAttribute("tripDTO", new TripDTO());
        List<TripDTO> tripList = tripRegisterService.findAll();
        model.addAttribute("tripList", tripList);
        return "select_trip";
    }

    /*@ModelAttribute("tripList")
    public List<TripDTO> getTrips(){
        List<TripDTO> tripList = tripRegisterService.findAll();
        return tripList;
    }*/

    @GetMapping("/select/search")
    public String searchTrip(@RequestParam Long tripId, Model model) {
        TripDTO trip = tripRegisterService.searchTripRegisterById(tripId);
        model.addAttribute("tripId",trip);
        /*if (trip != null) {
            model.addAttribute("tripList", List.of(trip));
        } else {
            model.addAttribute("tripList", List.of());
            model.addAttribute("errorMessage", "No se encontró un viaje con el ID ingresado.");
        }*/
        return "redirect:/select_trip"; // Retorna la misma vista con el resultado de la búsqueda
    }



    @PostMapping("/create")
    public String createTrip(@ModelAttribute TripVehicleDTO tripVehicleDTO) {
        tripRegisterService.createTripRegister2(tripVehicleDTO);
        return  "register_trip";
    }

    @GetMapping("/register")
    public String showRegisterTrip(Model model) {
        model.addAttribute("tripVehicleDTO", new TripVehicleDTO());
        return "register_trip";
    }

    



}
