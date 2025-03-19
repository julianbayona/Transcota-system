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
        return "redirect:/users/register/message";
    }

    @GetMapping("/register/message")
    public String registerMessage(Model model){
        model.addAttribute("errorMessage", "EL VEHICULO SE REGISTRO CON EXITO.");
        model.addAttribute("userDTO", new UserDTO());
        return "/register_user";
    }

    

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "update_user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        driverService.updateDriver(userDTO.getPersonId(), userDTO);
        return "redirect:/users/update/message";
    }

    @GetMapping("/update/message")
    public String updateMessage(Model model){
        model.addAttribute("errorMessage", "EL VEHICULO SE ACTUALIZO CON EXITO.");
        model.addAttribute("userDTO", new UserDTO());
        return "/update_user";
    }

    @PostMapping("/update/search")
    public String searchUser(@RequestParam("personId") Long id, Model model) {
        UserDTO foundUser = driverService.searchId(id);
        if (foundUser == null) {
            model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
            model.addAttribute("userDTO", new UserDTO());
            return "update_user";  
        }
        model.addAttribute("userDTO", foundUser);
        return "update_user";
    }

    @GetMapping("/select")
    public String showusersList(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("userList", driverService.findAll());
        return "select_user"; 
    }

    @PostMapping("/select/search")
    public String searchUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model){
        Long id = userDTO.getPersonId();
        UserDTO user = driverService.searchId(id);
        if(user == null){
            model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
            model.addAttribute("UserList", new ArrayList<>());
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
            model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
            model.addAttribute("userDTO", new UserDTO());
            return "delete_user";  
        }
        model.addAttribute("userDTO", foundUser);
        return "delete_user";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam("personId") Long id, Model model){
        driverService.deleteUser(id);
        return "redirect:/users/delete/message";
    }

    @GetMapping("/delete/message")
    public String deleteMessage(Model model){
        model.addAttribute("errorMessage", "EL VEHICULO SE ELIMINO CON EXITO.");
        model.addAttribute("userDTO", new UserDTO());
        return "/delete_user";
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
