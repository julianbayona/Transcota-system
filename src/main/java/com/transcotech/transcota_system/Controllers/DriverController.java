package com.transcotech.transcota_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.transcotech.transcota_system.Service.DriverService;

@Controller
@RequestMapping("api/drivers")
public class DriverController {

    @Autowired
    private DriverService vehicleService;

    @GetMapping
    public String showAllVehicles(Model model){
        model.addAttribute("vehicles", vehicleService.findAll());
        return "all-Vehicles";
    }


    
}
