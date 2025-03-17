package com.transcotech.transcota_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.TripMapper;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.repositories.TripRegisterRepositoryInterface;
@Service
public class TripRegisterService implements TripRegisterServiceInterface{


    private final TripMapper tripMapper = TripMapper.INSTANCE;
    @Autowired
    private DriverService driverService;
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TripRegisterRepositoryInterface tripRegisterRepositoryInterface;

    @Override
    public List<TripRegister> findAll() {
        return tripRegisterRepositoryInterface.findAll();
    }

    @Override
    public TripDTO searchTripRegisterById(Long id) {
        TripRegister tripRegister = tripRegisterRepositoryInterface.findById(id).orElse(null);
        return tripMapper.tripToTripDTO(tripRegister);
    }

    @Override
    public void deleteTripRegister(Long id) {
        tripRegisterRepositoryInterface.deleteById(id);
    }

    @Override
    public TripRegister createTripRegister(TripDTO tripDTO) {
        TripRegister tripRegister = tripMapper.tripDTOToTrip(tripDTO);
        return this.tripRegisterRepositoryInterface.save(tripRegister);
    }

    public UserDTO searchDriverById(Long id){
        return driverService.searchId(id);
    }

    public VehicleDTO searchVehicleById(Long id){
        return vehicleService.searchId(id);
    }

}
