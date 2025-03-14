package com.transcotech.transcota_system.model;

import java.util.ArrayList;
import java.util.List;

public class DriverRole extends Role{

    private List<TripRegister> trips;

    public DriverRole(){
        trips = new ArrayList<TripRegister>();
    }

    public List<TripRegister> getTrips() {
        return trips;
    }

    public void setTrips(List<TripRegister> trips) {
        this.trips = trips;
    }
}
