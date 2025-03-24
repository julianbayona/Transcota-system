package com.transcotech.transcota_system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.Service.TripRegisterService;
import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;

@Controller
@RequestMapping("/trips")
public class TripRegisterController {

    @Autowired
    private TripRegisterService tripRegisterService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverService driverService;


    @GetMapping("/select")
    public String showSearchForm(Model model) {
        List<TripDTO> tripList = tripRegisterService.findAll();
        model.addAttribute("tripList", tripList);
        return "select_trip";
    }

    @PostMapping("/select/search")
    public String searchTrip(@RequestParam("tripId") Long tripId, Model model) {
        TripDTO trip = tripRegisterService.searchTripRegisterById(tripId);
        
        if (trip == null) {
            model.addAttribute("message", "No se encontró un viaje con el ID "+tripId+".");
            List<TripDTO> tripList = tripRegisterService.findAll();
            model.addAttribute("tripList", tripList);
            return "select_trip";
        }
        List<TripDTO> tripList = new ArrayList<>();
        tripList.add(trip);
        model.addAttribute("tripList", tripList);
        return "select_trip";
    }

    @PostMapping("/update")
    public String updateTrip(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model) {
        UserDTO driver = driverService.searchId(tripDTO.getDriverId().getPersonId());
        VehicleDTO vehicle = vehicleService.searchId(tripDTO.getVehicleId().getVehicleId());
        if(vehicle == null){
            model.addAttribute("message", "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.");
            model.addAttribute("alertType", "error");
            return "update_trip";
        }else if(driver == null){
            model.addAttribute("message", "El conductor con ID " + tripDTO.getDriverId().getPersonId() + " no existe.");
            model.addAttribute("alertType", "error");
            return "update_trip";
        }else{
            tripDTO.setDriverId(driver);
            tripDTO.setVehicleId(vehicle);
            tripRegisterService.updateTrip(tripDTO.getTripId(), tripDTO);   
            model.addAttribute("message","SE ACTUALIZO EL VIAJE CORRECTAMENTE");
            model.addAttribute("alertType", "success");
        }
        model.addAttribute("tripDTO", new TripDTO());
        return "update_trip";
    }

    @PostMapping("/update/search")
    public String searchTripUpdate(@RequestParam("tripId") Long tripId, Model model) {
        TripDTO trip = tripRegisterService.searchTripRegisterById(tripId);
        
        if (trip == null) {
            model.addAttribute("message", "No se encontró un viaje con el ID "+tripId+".");
            model.addAttribute("alertType", "info");
            model.addAttribute("tripDTO", new TripDTO());
            return "update_trip";
        } else {
            tripRegisterService.updateTrip(tripId, trip);
            model.addAttribute("tripDTO", trip);
        }
        return "update_trip";
    }

    @PostMapping("/update/searchVehicle")
    public String searchVehicleUpdate(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model){
        VehicleDTO vehicle = vehicleService.searchId(tripDTO.getVehicleId().getVehicleId());
        if(vehicle == null){
            model.addAttribute("message", "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.");
            model.addAttribute("alertType", "error");
            return "update_trip";
        }
        model.addAttribute("plate", vehicle.getPlate());
        model.addAttribute("model", vehicle.getModel());
        model.addAttribute("year", vehicle.getYear());
        model.addAttribute("vehicleType", vehicle.getType());
        return "update_trip";
    }

    @PostMapping("/update/searchDriver")
    public String searchDriverUpdate(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model){
        UserDTO driver = driverService.searchId(tripDTO.getDriverId().getPersonId());
        if(driver == null){
            model.addAttribute("message", "El vehiculo con ID " + tripDTO.getDriverId().getPersonId() + " no existe.");
            model.addAttribute("alertType", "error");
            return "update_trip";
        }
        model.addAttribute("name", driver.getName());
        model.addAttribute("role", driver.getRoleDTO());
        model.addAttribute("email", driver.getEmail());
        return "update_trip";
    }

    @GetMapping("/update")
    public String showUpdateTrip(Model model) {
        model.addAttribute("tripDTO", new TripDTO());
        return "update_trip";
    }

    @GetMapping("/register")
    public String registerTrip(Model model) {
        model.addAttribute("tripDTO", new TripDTO());
        return "register_trip";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model) {
        UserDTO driver = driverService.searchId(tripDTO.getDriverId().getPersonId());
        VehicleDTO vehicle = vehicleService.searchId(tripDTO.getVehicleId().getVehicleId());
        if(vehicle == null){
            model.addAttribute("message", "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.");
            model.addAttribute("alertType", "error");
            return "register_trip";
        }else if(driver == null){
            model.addAttribute("message", "El conductor con ID " + tripDTO.getDriverId().getPersonId() + " no existe.");
            model.addAttribute("alertType", "error");
            return "register_trip";
        }else{
            tripDTO.setDriverId(driver);
            tripDTO.setVehicleId(vehicle);
            tripRegisterService.createTripRegister(tripDTO);    
            model.addAttribute("message","SE REGISTRO EL VIAJE CORRECTAMENTE");
            model.addAttribute("alertType", "success");
        }
        model.addAttribute("tripDTO", new TripDTO());
        return "register_trip";
    }

    @PostMapping("/register/searchVehicle")
    public String searchVehicleRegister(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model){
        VehicleDTO vehicle = vehicleService.searchId(tripDTO.getVehicleId().getVehicleId());
        if(vehicle == null){
            model.addAttribute("message", "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.");
            model.addAttribute("alertType", "info");
            return "register_trip";
        }
        model.addAttribute("plate", vehicle.getPlate());
        model.addAttribute("model", vehicle.getModel());
        model.addAttribute("year", vehicle.getYear());
        model.addAttribute("vehicleType", vehicle.getType());
        return "register_trip";
    }

    @PostMapping("/register/searchDriver")
    public String searchDriverRegister(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model){
        UserDTO driver = driverService.searchId(tripDTO.getDriverId().getPersonId());
        if(driver == null){
            model.addAttribute("message", "El vehiculo con ID " + tripDTO.getDriverId().getPersonId() + " no existe.");
            model.addAttribute("alertType", "info");
            return "register_trip";
        }
        model.addAttribute("name", driver.getName());
        model.addAttribute("role", driver.getRoleDTO());
        model.addAttribute("email", driver.getEmail());
        return "register_trip";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("tripDTO", new TripDTO());
        return "delete_trip";
    }

    @PostMapping("/delete/search")
    public String searchDelete(@RequestParam("tripId") Long id, Model model){
        TripDTO trip = tripRegisterService.searchTripRegisterById(id);
        if (trip == null) {
            model.addAttribute("message", "No se encontró un viaje con el ID ingresado "+id+".");
            model.addAttribute("alertType", "error");
            model.addAttribute("tripDTO", new TripDTO());
            return "delete_trip";
        }
        model.addAttribute("tripDTO", trip);
        return "delete_trip";
    }

    @PostMapping("/delete")
    public String deleteTrip(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model) {
        if(tripDTO.getTripId() == null){
            model.addAttribute("message", "Debe buscar y verificar primero el viaje");
            model.addAttribute("alertType", "info");
            model.addAttribute("tripDTO", new TripDTO());
            return "delete_trip";
        }
        tripRegisterService.deleteTripRegister(tripDTO.getTripId());
        model.addAttribute("tripDTO", new TripDTO());
        model.addAttribute("message", "SE HA ELIMINADO EL VIAJE CON EXITO");
        model.addAttribute("alertType", "success");
        return "delete_trip";
    }

    @PostMapping("update/clear")
    public String clearUpdate(Model model){
        model.addAttribute("tripDTO", new TripDTO());
        return "redirect:/trips/update";
    }

    @PostMapping("register/clear")
    public String clearRegister(Model model){
        model.addAttribute("tripDTO", new TripDTO());
        return "redirect:/trips/register";
    }

}
