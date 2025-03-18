package com.transcotech.transcota_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.dto.UserDTO;
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
    public String showusersList(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        UserDTO user = new UserDTO();
        user.setPersonId(new Long(2));
        user.setEmail("edison@gmail");
        user.setName("Edison Ferney");
        user.setRoleDTO("Despachador");
        List<UserDTO> dtos = new ArrayList<>();
        //model.addAttribute("userList", driverService.findAll()); CUANDO HAYA BD
        model.addAttribute("userList", dtos);
        return "select_user"; 
    }

    @PostMapping("/select/search")
    public String searchUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model){
        Long id = userDTO.getPersonId();
        //UserDTO User = driverService.searchId(id); QUITAR COMENTARIO CUANDO SE CONECTE A BD
        UserDTO user = new UserDTO();
        user.setPersonId(new Long(2));
        user.setEmail("edison@gmail");
        user.setName("Edison Ferney");
        user.setRoleDTO("Despachador");
        if(user == null){
            model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
            model.addAttribute("UserList", new ArrayList<>());
            return "select_User";
        }
        

        List<UserDTO> dtos = new ArrayList<>();
        dtos.add(user);
        System.out.println("AQUI ESTOY");
        model.addAttribute("userList", dtos);
        return "select_user";
    }

    @PostMapping("/update/search")
    public String searchUser(@RequestParam("personId") Long id, Model model) {
        // Lista de vehículos con datos de prueba
        List<User> vs = new ArrayList<>();
        System.out.println(id + "QUE PUTAS");
        // Crear usuario de prueba
        User user = new User();
        user.setPersonId(new Long(2));
        user.setEmail("edison@gmail");
        user.setName("Edison Ferney");
        //user.setRole("Despachador");

        vs.add(user); // Agregar vehículo a la lista

        // Buscar vehículo en la lista sin usar equals()
        User foundUser = vs.stream()
            .filter(v -> v.getPersonId().equals(id))  // ✅ Comparación directa
            .findFirst()
            .orElse(null);
        System.out.println(foundUser);
        if (foundUser == null) {
            System.out.println("⚠ Vehículo con ID " + id + " no encontrado.");
            model.addAttribute("errorMessage", "El vehículo con ID " + id + " no existe.");
            model.addAttribute("userDTO", new UserDTO()); // 🔹 Se envía un objeto vacío a la vista
            return "update_user";  
        }
        System.out.println("HOLA");
        // Convertir el vehículo encontrado a DTO
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(foundUser);
        System.out.println("✅ Vehículo encontrado: " + userDTO.getEmail() + " - " + userDTO.getRoleDTO());
        
        model.addAttribute("userDTO", userDTO);
        return "update_user";  // Cargar la vista con los datos llenos
    }


    @PostMapping("/update")
    public String updateuser(@ModelAttribute("userDTO") UserDTO userDTO) {
        List<User> vs = new ArrayList<>();

        User user = new User();
        user.setPersonId(new Long(2));
        user.setEmail("edison@gmail");
        user.setName("Edison Ferney");
        //user.setRole("Despachador");

        vs.add(user); // Agregar vehículo a la lista

        // Buscar y actualizar el vehículo
        /*for (User v : vs) {
            if (v.getuserId() == userDTO.getuserId()) {  // ✅ Solución con ==
                v.setPlate(userDTO.getPlate());
                v.setModel(userDTO.getModel());
                v.setType(userDTO.getType());
                v.setYear(userDTO.getYear());

                // Imprimir en consola
                System.out.println("✅ Vehículo actualizado correctamente:");
                System.out.println("ID: " + v.getuserId());
                System.out.println("Placa: " + v.getPlate());
                System.out.println("Modelo: " + v.getModel());
                System.out.println("Año: " + v.getYear());
                System.out.println("Tipo: " + v.getType());
                break;
            }
        }*/

        return "redirect:/users/update";
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
