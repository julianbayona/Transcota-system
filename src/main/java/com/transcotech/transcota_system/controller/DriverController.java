package com.transcotech.transcota_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.model.Vehicle;

@Controller
@RequestMapping("/users")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "update_user";
    }

    @GetMapping("/select")
    public String showVehiclesList(Model model) {

        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("userList", driverService.findAll());
        return "select_user"; 
    }

    @PostMapping("/select/search")
    public String searchUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model){
        return "select_user";
    }

    //@GetMapping
    public String showAllDrivers(Model model){
        model.addAttribute("drivers", driverService.findAll());
        return "all-drivers";
    }

    @GetMapping("/all")
    public List<UserDTO> showAllDri(){
        return driverService.findAll();
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Long id){
        driverService.deleteUser(id);
        return "";
    }

    @PostMapping("/create")
    public String createDriver(@ModelAttribute UserDTO user){
        driverService.createDriver(user);
        return "register_user";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerVehicle(@RequestBody Vehicle vehicle) {
        // Lógica para registrar el vehículo
        return ResponseEntity.ok("Vehículo registrado correctamente");
    }

    @GetMapping("/register1")
    public String registerVehicle(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register_user";
    }


    
}
