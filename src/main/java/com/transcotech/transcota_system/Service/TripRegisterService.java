package com.transcotech.transcota_system.Service;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.repositories.TripRegisterRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TripRegisterService implements TripRegisterServiceInterface{

    @Autowired
    private TripRegisterRepositoryInterface tripRegisterRepositoryInterface;

    @Override
    public List<TripRegister> findAll() {
        return tripRegisterRepositoryInterface.findAll();
    }

    @Override
    public TripRegister searchTripRegisterById(Long id) {
        return tripRegisterRepositoryInterface.findById(id).orElse(null);
    }

    @Override
    public void deleteTripRegister(Long id) {
        tripRegisterRepositoryInterface.deleteById(id);
    }

    @Override
    public TripRegister createTripRegister(TripRegister tripRegister) {

        return this.tripRegisterRepositoryInterface.save(tripRegister);

    }

    public TripRegister dtoToObject(TripDTO tripDTO){
        TripRegister tripRegister = new TripRegister();
        return tripRegister;
    }


}
