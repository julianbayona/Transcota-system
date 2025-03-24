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
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;

@Controller
@RequestMapping("/users")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @GetMapping("/register")
    public String registerVehicle(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register_user";
    }

    @PostMapping("/register")
    public String registerDriver(@ModelAttribute UserDTO user, Model model){
        driverService.createDriver(user);
        addAtributeToModel(model, "EL USUARIO SE REGISTRO CON EXITO.", "success");
        return "register_user";
    }    

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        
        model.addAttribute("userDTO", new UserDTO());
        return "update_user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        if(userDTO.getPersonId() == null){
            addAtributeToModel(model, "Primero debe verificar el usuario", "info");
            return "update_user";  
        }
        UserDTO foundUser = driverService.searchId(userDTO.getPersonId());
        if (foundUser == null) {
            addAtributeToModel(model, "El usuario con ID " + userDTO.getPersonId() + " no existe.", "error");
            return "update_user";  
        }
        driverService.updateDriver(userDTO.getPersonId(), userDTO);
        return "redirect:/users/update/message";
    }

    @GetMapping("/update/message")
    public String updateMessage(Model model){
        addAtributeToModel(model, "EL USUARIO SE ACTUALIZO CON EXITO.", "success");
        return "/update_user";
    }

    @PostMapping("/update/search")
    public String searchUser(@RequestParam("personId") Long id, Model model) {
        UserDTO foundUser = driverService.searchId(id);
        if (foundUser == null) {
            addAtributeToModel(model, "El usuario con ID " + id + " no existe.", "error");
            return "update_user";  
        }
        model.addAttribute("userDTO", foundUser);
        return "update_user";
    }

    @GetMapping("/select")
    public String showUsersList(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("userList", driverService.findAll());
        return "select_user"; 
    }

    @PostMapping("/select/search")
    public String searchUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model){
        Long id = userDTO.getPersonId();
        UserDTO user = driverService.searchId(id);
        if(user == null){
            model.addAttribute("message", "El usuario con ID " + id + " no existe.");
            model.addAttribute("userList", driverService.findAll());
            return "select_User";
        }
        List<UserDTO> dtos = new ArrayList<>();
        dtos.add(user);
        model.addAttribute("userList", dtos);
        return "select_user";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "delete_user";
    }

    @PostMapping("delete/search")
    public String deleteSearch(@RequestParam("personId") Long id, Model model){
        UserDTO foundUser = driverService.searchId(id);
        if (foundUser == null) {
            addAtributeToModel(model, "El usuario con ID " + id + " no existe.", "error");
            return "delete_user";  
        }
        model.addAttribute("userDTO", foundUser);
        return "delete_user";
    }

    @PostMapping("/delete")
    public String deleteVehicle(@RequestParam("userId") Long personId, Model model) {
        UserDTO foundUser = driverService.searchId(personId);
        if(foundUser == null){
            addAtributeToModel(model, "Debe buscar y verificar primero el usuario", "info");
            return "delete_user";
        }else if (!driverService.deleteUser(personId)) {
            addAtributeToModel(model, "No se puede eliminar porque el conductor tiene viajes asignados", "info");
            return "delete_user";
        }
        return "redirect:/users/delete/message";
    }

    @GetMapping("/delete/message")
    public String deleteMessage(Model model){
        addAtributeToModel(model, "EL USUARIO SE ELIMINO CON EXITO.", "success");
        return "/delete_user";
    }

    private void addAtributeToModel(Model model, String message, String alertType){
        model.addAttribute("message", message);
        model.addAttribute("alertType", alertType);
        model.addAttribute("userDTO", new UserDTO());
    }

    @GetMapping("/assigned")
    public String assignedVehicles(Model model){
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("vehicleList", new ArrayList<>());
        return "assigned_vehicle";
    }

    @PostMapping("/assigned/search")
    public String assignedSearch(@RequestParam("personId") Long personId, Model model){
        UserDTO userDTO = driverService.searchId(personId);
        if(userDTO == null){
            model.addAttribute("message", "El usuario con ID " + personId + " no existe.");
            model.addAttribute("userDTO", new UserDTO());
            return "assigned_vehicle";
        }
        List<VehicleDTO> vehicles = driverService.getVehiclesAssignedDriver(personId);
        if(vehicles.size() == 0){
            model.addAttribute("message", "EL USUARIO NO TIENE VEHICULOS ASIGNADOS.");
        }
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("vehicleList", vehicles);
        return "assigned_vehicle";
    }

    @PostMapping("update/clear")
    public String clearUpdate(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "redirect:/users/update";
    }

    @PostMapping("register/clear")
    public String clearRegister(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "redirect:/users/register";
    }

    
    
}
