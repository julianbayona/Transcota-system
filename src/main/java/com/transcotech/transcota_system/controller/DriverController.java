package com.transcotech.transcota_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.model.Vehicle;

@Controller
@RequestMapping("/users")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public String showAllDrivers(Model model){
        model.addAttribute("drivers", driverService.findAll());
        return "all-drivers";
    }

   

     @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register_user";
    }


    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Long id){
        driverService.deleteUser(id);
        return "";
    }

    @PostMapping("/create")
    public User createDriver(@RequestBody User user){
        return driverService.createDriver(user);
    }

      @PostMapping("/register")
    public ResponseEntity<String> registerVehicle(@RequestBody Vehicle vehicle) {
        // Lógica para registrar el vehículo
        return ResponseEntity.ok("Vehículo registrado correctamente");
    }

    
}
