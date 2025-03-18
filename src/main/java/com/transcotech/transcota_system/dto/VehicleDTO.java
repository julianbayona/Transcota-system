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
    private TypeVehicle type;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TypeVehicle getType() {
        return type;
    }

    public void setType(TypeVehicle type) {
        this.type = type;
    }
}
