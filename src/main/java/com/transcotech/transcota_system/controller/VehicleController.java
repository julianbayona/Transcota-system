package com.transcotech.transcota_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.model.Vehicle;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String showAllVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        return "all-vehicles"; 
    }

    @GetMapping("/search")
    public ResponseEntity<Vehicle> getVehicleById(@RequestParam("id") Long id) {
        Vehicle vehicle = vehicleService.searchId(id);
        return (vehicle != null) ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public boolean createVehicle(@RequestBody Vehicle vehicle) {
    return vehicleService.createVehicle(vehicle);
}

    @PutMapping("/update")
    public boolean updateVehicle(@RequestParam("id") Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/delete")
    public boolean deleteVehicle(@RequestParam("id") Long id) {
        return vehicleService.deleteVehicle(id);
    }

}
