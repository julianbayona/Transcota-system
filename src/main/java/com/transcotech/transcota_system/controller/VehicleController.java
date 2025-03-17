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
import org.springframework.web.bind.annotation.ResponseBody;

import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.model.Vehicle;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

   /* */ @GetMapping
    public String showAllVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        return "all-vehicles"; 
    }

    @GetMapping("/all")
    public List<Vehicle> showAllVehi(){
        return vehicleService.findAll();
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("vehicleDTO", new Vehicle());
        return "register_vehicle";
    }

    @PostMapping("/register")
    public String registerVehicle(@ModelAttribute("vehicleDTO") Vehicle vehicle, Model model) {
        boolean created = vehicleService.createVehicle(vehicle);
        
        if (created) {
            model.addAttribute("successMessage", "Vehículo registrado correctamente");
        } else {
            model.addAttribute("errorMessage", "Error al registrar vehículo");
        }

        return "register_vehicle"; 
    }

    @PostMapping("/create")
    @ResponseBody
    public void create(@RequestBody Vehicle vehicle) {
        vehicleService.createVehicle(vehicle); 
    }

    @GetMapping("/search")
    public String getVehicleById(@RequestParam("id") Long id, Model model) {
        Vehicle vehicle = vehicleService.searchId(id);
        if (vehicle != null) {
            model.addAttribute("vehicle", vehicle);
            return "vehicle_detail"; 
        }
        model.addAttribute("errorMessage", "Vehículo no encontrado");
        return "all-vehicles";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("vehicleDTO", new Vehicle()); 
        return "update_vehicle"; 
    }
    


    @GetMapping("/delete")
public String showDeleteForm(Model model) {
   // model.addAttribute("vehicleDTO", new Vehicle()); 
    return "delete_vehicle"; 
}

@GetMapping("/delete/search")
public String searchVehicleForDelete(@RequestParam("id") Long id, Model model) {
    Vehicle vehicle = vehicleService.searchId(id);
    if (vehicle != null) {
        model.addAttribute("vehicleDTO", vehicle);
        return "delete_vehicle";
    }
    model.addAttribute("errorMessage", "Vehículo no encontrado");
    return "delete_vehicle";
}

@PostMapping("/delete")
public String deleteVehicle(@RequestParam("vehicleId") Long id, Model model) {
    boolean deleted = vehicleService.deleteVehicle(id);
    if (deleted) {
        model.addAttribute("successMessage", "Vehículo eliminado correctamente");
    } else {
        model.addAttribute("errorMessage", "Error al eliminar vehículo");
    }
    return "delete_vehicle"; 
}
@GetMapping("/select")
public String showVehiclesList(Model model) {
    model.addAttribute("vehicles", vehicleService.getAllVehicles()); // Cargar lista de vehículos
    return "select_vehicle"; 
}

}
