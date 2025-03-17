package com.transcotech.transcota_system.Service;

import java.util.List;

import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;

@Service
public class DriverService implements DriverServiceInterface{

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private DriverRepositoryInterface driverRepository;

    @Override
    public List<User> findAll(){
        return driverRepository.findAll();
    }

    @Override
    public UserDTO searchId(Long id){
        UserDTO userDTO = userMapper.userToUserDTO(driverRepository.findById(id).orElse(null));
        return userDTO;
    }

    @Override
    public void deleteUser(Long id){
        this.driverRepository.deleteById(id);
    }

    @Override
    public User createDriver(User user) {
        return this.driverRepository.save(user);
        
    }
    
}
