package com.transcotech.transcota_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepository;

@Service
public class VehicleService {

    @Autowired
    private DriverRepository driverRepository;

    public List<User> findAll(){
        return driverRepository.findAll();
    }

    public User searchId(int id){
        return driverRepository.searchId(id);
    }
    
}
