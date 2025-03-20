package com.transcotech.transcota_system.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.VehicleMapper;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.VehicleRepositoryInterface;

@Service
public class VehicleService implements VehicleServiceInterface {

    private List<Vehicle> vehiclesList = new ArrayList<>();

    private final VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    @Autowired
    private VehicleRepositoryInterface vehicleRepository;

    @Override
    public List<VehicleDTO> findAll() {
        return vehicleMapper.vehiclesToVehicleDTOs(vehicleRepository.findAll());
    }

    @Override
    public VehicleDTO searchId(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        return vehicleMapper.vehicleToVehicleDTO(vehicle);
    }

    @Override
    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlate(vehicle.getPlate());
        if (existingVehicle.isPresent()) {
            return false; // La placa ya existe
        }
        vehicleRepository.save(vehicle);
        return true; // Registro exitoso
    }

    public VehicleDTO searchPlate(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlate(vehicle.getPlate());
        return existingVehicle
        .map(vehicleMapper::vehicleToVehicleDTO) 
        .orElse(null);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    private void mostrarListaVehiculos() {
        System.out.println("Lista actualizada de vehículos:");
        vehicleRepository.findAll().forEach(System.out::println);
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    public void setVehiclesList(List<Vehicle> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    @Override
    public String updateVehicle(Long vehicleId, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);

        if (vehicle == null) {
            return "vehiculoInexistente"; } //No encontró el vehículo 

            Optional<Vehicle> existingVehicle = vehicleRepository.findByPlate(vehicleDTO.getPlate());

            // Verifica si la placa ya existe y no pertenece al mismo vehículo
            if (existingVehicle.isPresent() && existingVehicle.get().getVehicleId() != vehicleId) {
                return "placaOcupada";
            }
        // Actualizar los atributos
        vehicle.setPlate(vehicleDTO.getPlate());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setType(vehicleDTO.getType());
    
        vehicleRepository.save(vehicle); 
        return "actualizado"; 
    }

}