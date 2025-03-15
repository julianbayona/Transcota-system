package com.transcotech.transcota_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.transcotech.transcota_system.Service.DriverService;

@Controller
@RequestMapping("api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public String showAllVehicles(Model model){
        model.addAttribute("drivers", driverService.findAll());
        return "all-drivers";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable long id){
        driverService.deleteUser(id);
        return "";
    }

    
}
