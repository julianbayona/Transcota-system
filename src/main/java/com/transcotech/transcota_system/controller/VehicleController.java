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

import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.dto.VehicleDTO;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String showAllVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        return "all-vehicles";
    }

    // REGISTRAR--------------------------------------------------------------------

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "register_vehicle";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute VehicleDTO vehicleDTO, Model model) {
        boolean isCreated = vehicleService.createVehicle(vehicleDTO);
        if (isCreated) {
            addMessageToModel(model, "VEHICULO REGISTRADO CORRECTAMENTE", "success");
            model.addAttribute("vehicleDTO", new VehicleDTO());
            return "register_vehicle";
        } else {
            addMessageToModel(model, "Error: La placa ya está registrada.", "error");
        }
        return "register_vehicle";
    }

    @GetMapping("/select")
    public String showVehiclesList(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        model.addAttribute("vehicleList", vehicleService.getAllVehicles());
        return "select_vehicle";
    }

    @GetMapping("/assigned/user")
    public String getUserVehicles(@RequestParam String username, Model model) {
        model.addAttribute("vehicleList", vehicleService.getAllVehicles());
        return "assigned_vehicle";
    }

    @PostMapping("/select/search")
    public String searchVehicle(@ModelAttribute("vehicleDTO") VehicleDTO vehicleDTO, @RequestParam("vehicleId") Long id,
            Model model) {
        VehicleDTO vehicle = vehicleService.searchId(id);
        if (vehicle == null) {
            addMessageToModel(model, "El vehículo con ID " + id + " no existe.", "info");
            model.addAttribute("vehicleList", vehicleService.getAllVehicles());
            return "select_vehicle";
        }
        List<VehicleDTO> vehicleFound = new ArrayList<>();
        vehicleFound.add(vehicle);
        model.addAttribute("vehicleList", vehicleFound);
        return "select_vehicle";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "update_vehicle";
    }

    @PostMapping("/update/search")
    public String searchVehicle(@RequestParam("vehicleId") Long id, Model model) {
        VehicleDTO foundVehicle = vehicleService.searchId(id);
        if (foundVehicle == null) {
            addMessageToModel(model, "El vehículo con ID " + id + " no existe.", "info");
            model.addAttribute("vehicleDTO", new VehicleDTO());
            return "update_vehicle";
        }
        model.addAttribute("vehicleDTO", foundVehicle);
        return "update_vehicle";
    }

    @PostMapping("/update")
    public String updateVehicle(@ModelAttribute("vehicleDTO") VehicleDTO vehicleDTO, @RequestParam("vehicleId") Long id,
            Model model) {
        String operation = vehicleService.updateVehicle(id, vehicleDTO);

        if (operation.equals("vehiculoInexistente")) {
            addMessageToModel(model, "El vehículo con ID " + id + " no existe.", "error");
            model.addAttribute("vehicleDTO", new VehicleDTO());
            return "update_vehicle";
        }
        if (operation.equals("placaOcupada")) {
            Long idPlate = vehicleService.searchPlate(vehicleDTO).getVehicleId();
            addMessageToModel(model,
                    "la placa " + vehicleDTO.getPlate() + " ya esta en uso por el vehiculo con ID: " + idPlate,
                    "error");
            model.addAttribute("vehicleDTO", vehicleDTO);
            return "update_vehicle";
        }

        vehicleService.updateVehicle(id, vehicleDTO);
        addMessageToModel(model, "VEHICULO ACTUALIZADO CORRECTAMENTE", "success");
        model.addAttribute("vehicleDTO", vehicleDTO);
        return "update_vehicle";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "delete_vehicle";
    }

    @PostMapping("/delete/search")
    public String searchVehicleForDelete(@RequestParam("vehicleId") Long id, Model model) {
        VehicleDTO vehicle = vehicleService.searchId(id);

        if (vehicle != null) {
            model.addAttribute("vehicleDTO", vehicle);
        } else {
            addMessageToModel(model, "El vehículo con ID " + id + " no existe.", "info");
        }
        return "delete_vehicle";
    }

    @PostMapping("/delete")
    public String deleteVehicle(@RequestParam("vehicleId") Long vehicleId, Model model) {
        if (vehicleService.hasRelatedTrips(vehicleId)) {
            addMessageToModel(model, "No se puede eliminar el vehiculo porque tiene viajes asignados", "info");
            model.addAttribute("vehicleDTO", new VehicleDTO());
            return "delete_vehicle";
        } else if (vehicleService.deleteVehicle(vehicleId)) {
            addMessageToModel(model, "VEHICULO ELIMINADO CORRECTAMENTE", "success");
            model.addAttribute("vehicleDTO", new VehicleDTO());
            return "delete_vehicle";
        }
        addMessageToModel(model, "Debe buscar y verificar primero el vehiculo", "info");
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "delete_vehicle";
    }

    private void addMessageToModel(Model model, String message, String alertType) {
        model.addAttribute("message", message);
        model.addAttribute("alertType", alertType);
    }

    @PostMapping("register/clear")
    public String clearRegister(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "redirect:/vehicles/register";
    }

    @PostMapping("update/clear")
    public String clearUpdate(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "redirect:/vehicles/update";
    }

}
