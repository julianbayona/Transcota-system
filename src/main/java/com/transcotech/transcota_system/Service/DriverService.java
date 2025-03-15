package com.transcotech.transcota_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;

@Service
public class DriverService implements DriverServiceInterface{

    @Autowired
    private DriverRepositoryInterface driverRepository;

    public List<User> findAll(){
        return driverRepository.findAll();
    }

    public User searchId(Long id){
        return driverRepository.findById(id).orElse(null);
    }

    public void deleteUser(long id){
        this.driverRepository.deleteById(id);
    }
    
}
