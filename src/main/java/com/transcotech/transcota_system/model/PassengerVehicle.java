package com.transcotech.transcota_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "passenger_vehicle")
@PrimaryKeyJoinColumn(name = "vehicleId")
public class PassengerVehicle extends Vehicle{

    public PassengerVehicle(){

    }

    public PassengerVehicle(String plate, int id ){
        super.setPlate(plate);
        super.setVehicleId(id);
    }


}
