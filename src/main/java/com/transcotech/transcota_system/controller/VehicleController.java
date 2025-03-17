/*package com.transcotech.transcota_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.model.Vehicle;

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

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("vehicleDTO", new Vehicle());
        return "register_vehicle";
    }

    @PostMapping("/register")
    public String registerVehicle(@ModelAttribute("vehicleDTO") Vehicle vehicle, Model model) {
        boolean created = vehicleService.createVehicle(vehicle);
        
        if (created) {
            model.addAttribute("successMessage", "Vehículo registrado correctamente");
        } else {
            model.addAttribute("errorMessage", "Error al registrar vehículo");
        }

        return "register_vehicle"; 
    }

    @GetMapping("/search")
    public String getVehicleById(@RequestParam("id") Long id, Model model) {
        Vehicle vehicle = vehicleService.searchId(id);
        if (vehicle != null) {
            model.addAttribute("vehicle", vehicle);
            return "vehicle_detail"; 
        }
        model.addAttribute("errorMessage", "Vehículo no encontrado");
        return "all-vehicles";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("vehicleDTO", new Vehicle()); 
        return "update_vehicle"; 
    }
    


    @GetMapping("/delete")
public String showDeleteForm(Model model) {
    model.addAttribute("vehicleDTO", new Vehicle()); 
    return "delete_vehicle"; 
}

@GetMapping("/delete/search")
public String searchVehicleForDelete(@RequestParam("id") Long id, Model model) {
    Vehicle vehicle = vehicleService.searchId(id);
    if (vehicle != null) {
        model.addAttribute("vehicleDTO", vehicle);
        return "delete_vehicle";
    }
    model.addAttribute("errorMessage", "Vehículo no encontrado");
    return "delete_vehicle";
}

@PostMapping("/delete")
public String deleteVehicle(@RequestParam("vehicleId") Long id, Model model) {
    boolean deleted = vehicleService.deleteVehicle(id);
    if (deleted) {
        model.addAttribute("successMessage", "Vehículo eliminado correctamente");
    } else {
        model.addAttribute("errorMessage", "Error al eliminar vehículo");
    }
    return "delete_vehicle"; 
}
@GetMapping("/select")
public String showVehiclesList(Model model) {
    model.addAttribute("vehicles", vehicleService.getAllVehicles()); // Cargar lista de vehículos
    return "select_vehicle"; 
}

}*/
package com.transcotech.transcota_system.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.VehicleMapper;
import com.transcotech.transcota_system.model.Vehicle;

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


    //REGISTRAR--------------------------------------------------------------------

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "register_vehicle";
    }
    @PostMapping("register")
    public String register(VehicleDTO vehicleDTO ,Model model){
        System.out.println("correcto");
        Vehicle vehicle = VehicleMapper.INSTANCE.vehicleDTOToLoadingVehicle(vehicleDTO); 
        System.out.println(vehicle.toString());
        //vehicleService.createVehicle(vehicle);
        return "register_vehicle";
    }

    //ACTUALIZAR ----------------------------------------------------------------------------

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("vehicleDTO", new Vehicle());
        return "update_vehicle";
    }

    @PostMapping("/update/search")
public String searchVehicle(@RequestParam("vehicleId") Long id, Model model) {
    //Vehicle vehicle = vehicleService.searchId(id);
    ArrayList <Vehicle> vs = new ArrayList<Vehicle>();
    Vehicle veic = new Vehicle();
    veic.setModel("a");
    veic.setPlate("123");
    veic.setType("Carga");
    veic.setVehicleId(21);
    veic.setYear(1339);

    Vehicle dind = null;
        for (Vehicle v : vs) {
            if (v.getVehicleId() == id) {
                dind = v;
                break; // Como los números de patas no se repiten, podemos salir del bucle
            }
        }

    if (dind == null) {
        model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
        return "update_vehicle";  // Recargar la vista sin llenar datos
    }

    // Convertir el vehículo a DTO si es necesario
    VehicleDTO vehicleDTO = VehicleMapper.INSTANCE.vehicleToVehicleDTO(dind);
    
    model.addAttribute("vehicleDTO", vehicleDTO);
    return "update_vehicle";  // Cargar la vista con los datos llenos
}


    @PostMapping("/update")
    public String updateVehicle(@RequestParam("vehicleId") Long id, @ModelAttribute("vehicleDTO") Vehicle vehicle, Model model) {
        boolean updated = vehicleService.updateVehicle(id, vehicle);
        if (updated) {
            System.out.println("Vehículo actualizado: " + vehicle);
            model.addAttribute("successMessage", "Vehículo actualizado correctamente");
        } else {
            model.addAttribute("errorMessage", "Error al actualizar vehículo");
        }
        return "update_vehicle";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("vehicleDTO", new Vehicle());
        return "delete_vehicle";
    }

    @PostMapping("/delete")
    public String deleteVehicle(@RequestParam("vehicleId") Long id, Model model) {
        boolean deleted = vehicleService.deleteVehicle(id);
        if (deleted) {
            System.out.println("Vehículo con ID " + id + " eliminado.");
            model.addAttribute("successMessage", "Vehículo eliminado correctamente");
        } else {
            model.addAttribute("errorMessage", "Error al eliminar vehículo");
        }
        return "delete_vehicle";
    }
}

