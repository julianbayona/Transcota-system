package com.transcotech.transcota_system.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.VehicleMapper;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.TripRegisterRepositoryInterface;
import com.transcotech.transcota_system.repositories.VehicleRepositoryInterface;

@Service
public class VehicleService implements VehicleServiceInterface {

    private List<Vehicle> vehiclesList = new ArrayList<>();

    private final VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    @Autowired
    private VehicleRepositoryInterface vehicleRepository;
    @Autowired
    private TripRegisterRepositoryInterface tripRepository;

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

    public boolean hasRelatedTrips(Long id){
        List<TripRegister> trips = tripRepository.findAll();
        for (TripRegister tripRegister : trips) {
            if(tripRegister.getVehicleId().getVehicleId() == id){
                return true;
            };
        }
        return false;
    }

    @Override
    public boolean createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlate(vehicle.getPlate());
        if (existingVehicle.isPresent()) {
            return false;
        }
        vehicleRepository.save(vehicle);
        return true; 
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
            return "vehiculoInexistente"; }

            Optional<Vehicle> existingVehicle = vehicleRepository.findByPlate(vehicleDTO.getPlate());

            
            if (existingVehicle.isPresent() && existingVehicle.get().getVehicleId() != vehicleId) {
                return "placaOcupada";
            }
        
        vehicle.setPlate(vehicleDTO.getPlate());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setType(vehicleDTO.getType());
    
        vehicleRepository.save(vehicle); 
        return "actualizado"; 
    }

}