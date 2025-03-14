package com.transcotech.transcota_system.model;

import java.util.ArrayList;
import java.util.List;

public class AdminRole extends Role{

    private List<Vehicle> managedVehicles;
    private List<TripRegister> managedTrips;
    private List<User> managedDrivers;

    public AdminRole(){
        managedVehicles = new ArrayList<Vehicle>();
        managedTrips = new ArrayList<TripRegister>();
        managedDrivers = new ArrayList<User>();
    }

    public List<Vehicle> getManagedVehicles() {
        return managedVehicles;
    }

    public void setManagedVehicles(List<Vehicle> managedVehicles) {
        this.managedVehicles = managedVehicles;
    }

    public List<TripRegister> getManagedTrips() {
        return managedTrips;
    }

    public void setManagedTrips(List<TripRegister> managedTrips) {
        this.managedTrips = managedTrips;
    }

    public List<User> getManagedDrivers() {
        return managedDrivers;
    }

    public void setManagedDrivers(List<User> managedDrivers) {
        this.managedDrivers = managedDrivers;
    }
}
