package com.transcotech.transcota_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.transcotech.transcota_system.Service.VehicleService;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/allVehicles")
    public String showAllVehicles(Model model){
        model.addAttribute("vehicles", vehicleService.findAll());
        return "all-Vehicles";
    }


    
}
