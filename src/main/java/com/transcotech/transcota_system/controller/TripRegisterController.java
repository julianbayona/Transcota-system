package com.transcotech.transcota_system.controller;

import java.util.List;

import com.transcotech.transcota_system.dto.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.transcotech.transcota_system.Service.TripRegisterService;
import com.transcotech.transcota_system.dto.TripDTO;

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
    public void createTrip(@RequestBody TripDTO tripDTO) {

        tripRegisterService.createTripRegister(tripDTO);
    }

    @PostMapping("/register")
    public String registerTrip(@ModelAttribute("trip") TripDTO tripDTO, RedirectAttributes redirectAttributes) {
        try {
            tripRegisterService.createTripRegister(tripDTO);
            redirectAttributes.addFlashAttribute("success", "Viaje registrado con éxito!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el viaje.");
        }
        return "redirect:/trips/register"; //
    }



}
