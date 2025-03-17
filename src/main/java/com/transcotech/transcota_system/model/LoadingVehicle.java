package com.transcotech.transcota_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loading_vehicle")
@PrimaryKeyJoinColumn(name = "vehicleId")
public class LoadingVehicle extends Vehicle{

    public LoadingVehicle(String plate, Long id ){
        super.setPlate(plate);
        super.setVehicleId(id);
    }
}
