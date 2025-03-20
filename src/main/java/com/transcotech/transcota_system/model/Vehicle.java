package com.transcotech.transcota_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleId;
    @Column(nullable = false, unique = true)
    private String plate;
    private String model;
    private Long year;
    @Enumerated(EnumType.STRING)
    private TypeVehicle type;


    

    public Vehicle(){
        
    }

    public Vehicle(String model, String plate, TypeVehicle type, Long year) {
        this.model = model;
        this.plate = plate;
        this.type = type;
        this.year = year;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public TypeVehicle getType() {
        return type;
    }

    public void setType(TypeVehicle type) {
        this.type = type;
    }

    
}