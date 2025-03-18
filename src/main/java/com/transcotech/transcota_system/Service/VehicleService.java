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
            mostrarListaVehiculos();
            return true;
        }
        return false;
    }

    @Override
    public boolean createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        if (vehicleRepository.existsById(vehicle.getVehicleId())) {
            return false;
        }
        vehicleRepository.save(vehicle);
        return true;
    }
    

    @Override
    public boolean updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle updatedVehicle = existingVehicle.get();
            updatedVehicle.setPlate(vehicle.getPlate());
            updatedVehicle.setModel(vehicle.getModel());
            updatedVehicle.setYear(vehicle.getYear());
            vehicleRepository.save(updatedVehicle);
            System.out.println("Vehículo actualizado: " + updatedVehicle);
            mostrarListaVehiculos();
            return true;
        }
        return false;
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
}


