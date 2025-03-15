package com.transcotech.transcota_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/all")
    public String showAllVehicles(Model model){
        model.addAttribute("vehicles", driverService.findAll());
        return "all-Vehicles";
    }

    @PostMapping("create")
    public User createDriver(@RequestBody User user){
        return driverService.createDriver(user);
    }


    
}
