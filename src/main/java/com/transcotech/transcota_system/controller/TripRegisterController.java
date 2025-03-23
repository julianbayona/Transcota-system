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
            addAtributeToModel(model, "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.", "error");
            return "update_trip";
        }else if(driver == null){
            addAtributeToModel(model, "El conductor con ID " + tripDTO.getDriverId().getPersonId() + " no existe.", "error");
            return "update_trip";
        }else{
            tripDTO.setDriverId(driver);
            tripDTO.setVehicleId(vehicle);
            tripRegisterService.updateTrip(tripDTO.getTripId(), tripDTO);  
            addAtributeToModel(model, "SE ACTUALIZO EL VIAJE CORRECTAMENTE", "success");
        }
        model.addAttribute("tripDTO", new TripDTO());
        return "update_trip";
    }

    @PostMapping("/update/search")
    public String searchTripUpdate(@RequestParam("tripId") Long tripId, Model model) {
        TripDTO trip = tripRegisterService.searchTripRegisterById(tripId);
        
        if (trip == null) {
            addAtributeToModel(model, "No se encontró un viaje con el ID "+tripId+".", "info");
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
        if (tripDTO.getVehicleId().getVehicleId() == null) {
            addAtributeToModel(model, "Debe ingresar primero el id de un vehiculo", "info");
            return "update_trip";
        }
        VehicleDTO vehicle = vehicleService.searchId(tripDTO.getVehicleId().getVehicleId());
        if(vehicle == null){
            addAtributeToModel(model, "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.", "error");
            return "update_trip";
        }
        putVehicleValues(model, vehicle);
        return "update_trip";
    }

    

    @PostMapping("/update/searchDriver")
    public String searchDriverUpdate(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model){
        if (tripDTO.getDriverId().getPersonId() == null) {
            addAtributeToModel(model, "Debe ingresar primero el id de un coductor", "info");
            return "update_trip";
        }
        UserDTO driver = driverService.searchId(tripDTO.getDriverId().getPersonId());
        if(driver == null){
            addAtributeToModel(model, "El vehiculo con ID " + tripDTO.getDriverId().getPersonId() + " no existe.", "error");
            return "update_trip";
        }
        putDriverValues(model, driver);
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
            addAtributeToModel(model, "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.", "error");
            return "register_trip";
        }else if(driver == null){
            addAtributeToModel(model, "El conductor con ID " + tripDTO.getDriverId().getPersonId() + " no existe.", "error");
            return "register_trip";
        }else{
            registerTrip(driver, tripDTO, vehicle); 
            addAtributeToModel(model, "SE REGISTRO EL VIAJE CORRECTAMENTE", "success"); 
        }
        model.addAttribute("tripDTO", new TripDTO());
        return "register_trip";
    }

    private void registerTrip(UserDTO driver, TripDTO tripDTO, VehicleDTO vehicle){
        tripDTO.setDriverId(driver);
        tripDTO.setVehicleId(vehicle);
        tripRegisterService.createTripRegister(tripDTO);   
    }


    @PostMapping("/register/searchVehicle")
    public String searchVehicleRegister(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model){
        if (tripDTO.getVehicleId().getVehicleId() == null) {
            addAtributeToModel(model, "Debe ingresar primero el id de un vehiculo", "info");
            return "register_trip";
        }
        VehicleDTO vehicle = vehicleService.searchId(tripDTO.getVehicleId().getVehicleId());
        if(vehicle == null){
            addAtributeToModel(model, "El vehiculo con ID " + tripDTO.getVehicleId().getVehicleId() + " no existe.", "info");
            return "register_trip";
        }
        putVehicleValues(model, vehicle);
        return "register_trip";
    }

    private void putVehicleValues(Model model, VehicleDTO vehicle){
        model.addAttribute("plate", vehicle.getPlate());
        model.addAttribute("model", vehicle.getModel());
        model.addAttribute("year", vehicle.getYear());
        model.addAttribute("vehicleType", vehicle.getType());
    }

    @PostMapping("/register/searchDriver")
    public String searchDriverRegister(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model){
        if (tripDTO.getDriverId().getPersonId() == null) {
            addAtributeToModel(model, "Debe ingresar primero el id de un coductor", "info");
            return "register_trip";
        }
        UserDTO driver = driverService.searchId(tripDTO.getDriverId().getPersonId());
        if(driver == null){
            addAtributeToModel(model, "El vehiculo con ID " + tripDTO.getDriverId().getPersonId() + " no existe.", "info");
            return "register_trip";
        }
        putDriverValues(model, driver);
        return "register_trip";
    }

    private void putDriverValues(Model model, UserDTO driver){
        model.addAttribute("name", driver.getName());
        model.addAttribute("role", driver.getRoleDTO());
        model.addAttribute("email", driver.getEmail());
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
            addAtributeToModel(model, "No se encontró un viaje con el ID ingresado "+id+".", "error");
            model.addAttribute("tripDTO", new TripDTO());
            return "delete_trip";
        }
        model.addAttribute("tripDTO", trip);
        return "delete_trip";
    }

    @PostMapping("/delete")
    public String deleteTrip(@ModelAttribute("tripDTO") TripDTO tripDTO, Model model) {
        if(tripDTO.getTripId() == null){
            addAtributeToModel(model, "Debe buscar y verificar primero el viaje", "info");
            model.addAttribute("tripDTO", new TripDTO());
            return "delete_trip";
        }
        tripRegisterService.deleteTripRegister(tripDTO.getTripId());
        model.addAttribute("tripDTO", new TripDTO());
        addAtributeToModel(model, "SE HA ELIMINADO EL VIAJE CON EXITO", "success");
        return "delete_trip";
    }

    private void addAtributeToModel(Model model, String message, String alertType){
        model.addAttribute("message", message);
        model.addAttribute("alertType", alertType);
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
