package com.transcotech.transcota_system.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.UserMapper;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;
import com.transcotech.transcota_system.repositories.TripRegisterRepositoryInterface;

@Service
public class DriverService implements DriverServiceInterface{

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private DriverRepositoryInterface driverRepository;
    @Autowired
    private VehicleServiceInterface vehicleRepository;
    @Autowired
    private TripRegisterRepositoryInterface tripRepository;

    @Override
    public List<UserDTO> findAll(){
        return userMapper.usersToUserDTOs(driverRepository.findAll());
    }

    @Override
    public UserDTO searchId(Long id){
        UserDTO userDTO = userMapper.userToUserDTO(driverRepository.findById(id).orElse(null));
        return userDTO;
    }

    @Override
    public boolean deleteUser(Long id){
        List<TripRegister> trips = tripRepository.findAll();
        for (TripRegister tripRegister : trips) {
            if(tripRegister.getDriverId().getPersonId() == id){
                return false;
            };
        }
        this.driverRepository.deleteById(id);
        return true;
    }

    @Override
    public User createDriver(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
        return this.driverRepository.save(user);
    }

    @Override
    public User updateDriver(Long id, UserDTO userDTO) {
        Optional<User> existingUser = driverRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = userMapper.userDTOToUser(userDTO);
            driverRepository.save(updatedUser);
            return updatedUser;
        }
        System.out.println("Uusario actualizado");
        return null;
    }

    @Override
public List<VehicleDTO> getVehiclesAssignedDriver(Long driverId) {
    List<TripRegister> tripRegisters = tripRepository.findAll();
    Set<VehicleDTO> vehicleFind = new HashSet<>();

    for (TripRegister tripRegister : tripRegisters) {
        if (tripRegister.getDriverId().getPersonId().equals(driverId)) {
            VehicleDTO vehicle = vehicleRepository.searchId(tripRegister.getVehicleId().getVehicleId());
            vehicleFind.add(vehicle); 
        }
    }
    
    return new ArrayList<>(vehicleFind);
}


    
    
}
