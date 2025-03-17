package com.transcotech.transcota_system.Service;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.model.TripRegister;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TripRegisterServiceInterface {

    public List<TripRegister> findAll();
    public TripRegister searchTripRegisterById(Long id);
    public void deleteTripRegister(Long id);
    public TripRegister createTripRegister(TripDTO tripDTO);
}
