package com.transcotech.transcota_system.dto;

import com.transcotech.transcota_system.model.TypeVehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private long vehicleId;
    private String plate;
    private String model;
    private int year;
    private String type;
    
}
