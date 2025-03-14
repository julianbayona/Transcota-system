package com.transcotech.transcota_system.model;

import java.util.ArrayList;
import java.util.List;

public class DriverRole extends Role{

    private List<TripRegister> trips;

    public DriverRole(){
        trips = new ArrayList<TripRegister>();
    }


}
