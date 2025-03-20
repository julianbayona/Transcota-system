package com.transcotech.transcota_system.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripDTO {
    private Long tripId;
    private String origin;
    private String destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private UserDTO driverId;
    private VehicleDTO vehicleId;

    public TripDTO(){
        this.driverId = new UserDTO();
        this.vehicleId = new VehicleDTO();
    }
    
}
