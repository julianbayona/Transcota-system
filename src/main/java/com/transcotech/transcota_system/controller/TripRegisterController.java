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
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.model.TripVehicleDTO;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;
import com.transcotech.transcota_system.repositories.VehicleRepositoryInterface;

@Controller
@RequestMapping("/trips")
public class TripRegisterController {

    @Autowired
    private TripRegisterService tripRegisterService;

    @Autowired
    private VehicleRepositoryInterface vehicleRepository;

    @Autowired
    private DriverRepositoryInterface driverRepository;

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
        tripRegisterService.createTripRegister(tripVehicleDTO.getTripDTO());
        return  "register_trip";
    }

    @GetMapping("/register")
    public String showRegisterTrip(Model model) {
        model.addAttribute("tripDTO", new TripDTO());
        //System.out.println(new TripDTO().getDriverId());
        return "register_trip";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model) {
        System.out.println(tripDTO.getDriverId());
        //User driver = driverRepository
        model.addAttribute("tripDTO", new TripDTO());
        return "register_trip";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("tripDTO", new TripDTO());
        return "delete_trip";
    }

    @PostMapping("/delete/search")
    public String sharchToDelete(@RequestParam("tripX") Long id, Model model){
        TripDTO trip = tripRegisterService.searchTripRegisterById(id);
        model.addAttribute("tripDTO", trip);
        return "delete_trip";
    }


}
