package com.transcotech.transcota_system.Service;

import java.util.List;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.model.TripRegister;

public interface TripRegisterServiceInterface {

    public List<TripDTO> findAll();
    public TripDTO searchTripRegisterById(Long id);
    public void deleteTripRegister(Long id);
    public TripRegister createTripRegister(TripDTO tripDTO);
}
