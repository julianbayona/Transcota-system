/*package com.transcotech.transcota_system.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.VehicleRepositoryInterface;

@Service
public class VehicleService implements VehicleServiceInterface {

    @Autowired
    private VehicleRepositoryInterface vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle searchId(Long id) {
        return vehicleRepository.findById(id).orElse(null);
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
    public boolean createVehicle(Vehicle vehicle) {
        if (vehicleRepository.existsById(vehicle.getVehicleId())) {
            return false;
        }
        vehicleRepository.save(vehicle);
        return true;
    }

    @Override
    public boolean updateVehicle(Long id, Vehicle vehicle) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle updatedVehicle = existingVehicle.get();
            updatedVehicle.setPlate(vehicle.getPlate());
            updatedVehicle.setModel(vehicle.getModel());
            updatedVehicle.setYear(vehicle.getYear());
            vehicleRepository.save(updatedVehicle);
            return true;
        }
        return false;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}*/
// VehicleService.java
package com.transcotech.transcota_system.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.VehicleRepositoryInterface;

@Service
public class VehicleService implements VehicleServiceInterface {

    @Autowired
    private VehicleRepositoryInterface vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle searchId(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            System.out.println("Vehículo con ID " + id + " eliminado.");
            mostrarListaVehiculos();
            return true;
        }
        return false;
    }

    @Override
    public boolean createVehicle(Vehicle vehicle) {
        if (vehicleRepository.existsById(vehicle.getVehicleId())) {
            return false;
        }
        vehicleRepository.save(vehicle);
        System.out.println("Vehículo registrado: " + vehicle);
        mostrarListaVehiculos();
        return true;
    }

    @Override
    public boolean updateVehicle(Long id, Vehicle vehicle) {
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
}
