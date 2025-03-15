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

    @Override
    public List<User> findAll(){
        return driverRepository.findAll();
    }

    @Override
    public User searchId(Integer id){
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Integer id){
        this.driverRepository.deleteById(id);
    }

    @Override
    public User createDriver(User user) {
        return this.driverRepository.save(user);
        
    }
    
}
