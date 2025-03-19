package com.transcotech.transcota_system.Service;

import java.util.List;

import com.transcotech.transcota_system.dto.VehicleDTO;

public interface VehicleServiceInterface {
    List<VehicleDTO> findAll();
    VehicleDTO searchId(Long id);
    boolean deleteVehicle(Long id);
    boolean createVehicle(VehicleDTO vehicleDTO);
    String updateVehicle(Long id, VehicleDTO vehicleDTO);
}
