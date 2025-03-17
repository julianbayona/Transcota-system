package com.transcotech.transcota_system.dto;

import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {
    private long tripId;
    private String origin;
    private String destination;
    private String date;
    private Long driverDTO;
    private Long vehicleDTO;
    
}
