package com.transcotech.transcota_system.model;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripVehicleDTO {
    private TripDTO tripDTO;
    private VehicleDTO vehicleDTO;
    private UserDTO userDTO;
    
}
