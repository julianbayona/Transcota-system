package com.transcotech.transcota_system.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.transcotech.transcota_system.model.DriverRole;
import com.transcotech.transcota_system.model.User;


@Repository
public class DriverRepository {

    public List<User> findAll(){
        List<User> x = new ArrayList<>();
        x.add(new User(new DriverRole(),1,"j","em"));
        x.add(new User(new DriverRole(),2,"a","em"));
        x.add(new User(new DriverRole(),3,"b","em"));
        x.add(new User(new DriverRole(),4,"g","em"));
        return x;
    }

    public User searchId(int idVehicle){
        User c = new User(new DriverRole(),1,"j","em");
        return c;
    }
    
}
