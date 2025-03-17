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
import java.util.List;

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
    private VehicleDTO vehicleDTO;

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
    model.addAttribute("vehicleDTO", new VehicleDTO());  // Se inicializa un objeto vacío
    return "update_vehicle";
}

@PostMapping("/update/search")
public String searchVehicle(@RequestParam("vehicleId") Long id, Model model) {
    // Lista de vehículos con datos de prueba
    List<Vehicle> vs = new ArrayList<>();

    // Crear vehículo de prueba
    Vehicle veic = new Vehicle();
    veic.setModel("Toyota");
    veic.setPlate("ABC123");
    veic.setType("Carga");
    veic.setVehicleId(21); // ID de prueba
    veic.setYear(2020);

    vs.add(veic); // Agregar vehículo a la lista

    // Buscar vehículo en la lista sin usar equals()
    Vehicle foundVehicle = vs.stream()
        .filter(v -> v.getVehicleId() == id)  // ✅ Comparación directa
        .findFirst()
        .orElse(null);

    if (foundVehicle == null) {
        System.out.println("⚠ Vehículo con ID " + id + " no encontrado.");
        model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
        model.addAttribute("vehicleDTO", new VehicleDTO()); // 🔹 Se envía un objeto vacío a la vista
        return "update_vehicle";  
    }

    // Convertir el vehículo encontrado a DTO
    VehicleDTO vehicleDTO = VehicleMapper.INSTANCE.vehicleToVehicleDTO(foundVehicle);
    System.out.println("✅ Vehículo encontrado: " + vehicleDTO.getPlate() + " - " + vehicleDTO.getType());

    model.addAttribute("vehicleDTO", vehicleDTO);
    return "update_vehicle";  // Cargar la vista con los datos llenos
}


@PostMapping("/update")
public String updateVehicle(@ModelAttribute("vehicleDTO") VehicleDTO vehicleDTO) {
    List<Vehicle> vs = new ArrayList<>();

    Vehicle veic = new Vehicle();
    veic.setModel("Toyota");
    veic.setPlate("ABC123");
    veic.setType("Carga");
    veic.setVehicleId(21); // ID de prueba
    veic.setYear(2020);

    vs.add(veic); // Agregar vehículo a la lista

    // Buscar y actualizar el vehículo
    for (Vehicle v : vs) {
        if (v.getVehicleId() == vehicleDTO.getVehicleId()) {  // ✅ Solución con ==
            v.setPlate(vehicleDTO.getPlate());
            v.setModel(vehicleDTO.getModel());
            v.setType(vehicleDTO.getType());
            v.setYear(vehicleDTO.getYear());

            // Imprimir en consola
            System.out.println("✅ Vehículo actualizado correctamente:");
            System.out.println("ID: " + v.getVehicleId());
            System.out.println("Placa: " + v.getPlate());
            System.out.println("Modelo: " + v.getModel());
            System.out.println("Año: " + v.getYear());
            System.out.println("Tipo: " + v.getType());
            break;
        }
    }

    return "redirect:/vehicles/update";
}

// VER VEHICULOS-----------------------

@GetMapping("/select")
public String showVehiclesList(Model model) {

    model.addAttribute("vehicleDTO", new VehicleDTO());
vehicleService.getVehiclesList().clear();
    Vehicle v1 = new Vehicle();
    v1.setModel("mazda");
    v1.setPlate("ABC123");
    v1.setType("Carga");
    v1.setVehicleId(21); // ID de prueba
    v1.setYear(2020);

    Vehicle v2 = new Vehicle();
    v2.setModel("Toyota");
    v2.setPlate("ABC");
    v2.setType("Pasajeros");
    v2.setVehicleId(22); // ID de prueba
    v2.setYear(2020);

    vehicleService.getVehiclesList().add(v1);
    vehicleService.getVehiclesList().add(v2);


    List <VehicleDTO> dtos = new ArrayList<VehicleDTO>();
    for(Vehicle vehicle:vehicleService.getVehiclesList()){
        dtos.add(VehicleMapper.INSTANCE.vehicleToVehicleDTO(vehicle));
    }

    model.addAttribute("vehicleList", dtos); // Cargar lista de vehículos
    return "select_vehicle"; 
}

@PostMapping("/select/search")
public String searchVehicle(@ModelAttribute("vehicleDTO") VehicleDTO vehicleDTO, Model model) {
    Long id = vehicleDTO.getVehicleId();

    // Buscar el vehículo en la lista
    Vehicle foundVehicle = vehicleService.getVehiclesList().stream()
    .filter(v -> v.getVehicleId() == id)  // ✅ Comparación directa
    .findFirst()
    .orElse(null);

    if (foundVehicle == null) {
        model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
        model.addAttribute("vehicleList", new ArrayList<>()); // Evita mostrar datos erróneos
        return "select_vehicle";
    }

    // Si se encuentra, actualizar la lista con solo ese vehículo
    List<VehicleDTO> dtos = new ArrayList<>();
    dtos.add(VehicleMapper.INSTANCE.vehicleToVehicleDTO(foundVehicle));

    model.addAttribute("vehicleList", dtos);
    return "select_vehicle";
}
    //ELIMINAR ------------------------------------------------------------------------

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("vehicleDTO", new Vehicle());
        return "delete_vehicle";
    }
    

    @PostMapping("/delete/search")
public String searchVehicleForDelete(@RequestParam("id") Long id, Model model) {
    Vehicle vehicle = vehicleService.getVehiclesList().stream()
        .filter(v -> v.getVehicleId() == id)
        .findFirst()
        .orElse(null);

    if (vehicle != null) {
        model.addAttribute("vehicleDTO", VehicleMapper.INSTANCE.vehicleToVehicleDTO(vehicle));
    } else {
        model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
    }
    return "delete_vehicle";
}
    
@PostMapping("/delete")
public String deleteVehicle(@RequestParam("vehicleId") Long id, Model model) {
    boolean removed = vehicleService.getVehiclesList().removeIf(v -> v.getVehicleId() == id);

    if (removed) {
        model.addAttribute("successMessage", "Vehículo eliminado correctamente.");
    } else {
        model.addAttribute("errorMessage", "No se encontró el vehículo con ID " + id);
    }

    return "delete_vehicle";
}

    


}

