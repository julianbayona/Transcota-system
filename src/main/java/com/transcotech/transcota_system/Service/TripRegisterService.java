package com.transcotech.transcota_system.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.mapper.TripMapper;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.model.TripVehicleDTO;
import com.transcotech.transcota_system.repositories.TripRegisterRepositoryInterface;
@Service
public class TripRegisterService implements TripRegisterServiceInterface{


    private final TripMapper tripMapper = TripMapper.INSTANCE;

    @Autowired
    private TripRegisterRepositoryInterface tripRegisterRepositoryInterface;

    @Override
    public List<TripDTO> findAll() {
        List<TripRegister> trips = tripRegisterRepositoryInterface.findAll();
        List<TripDTO> tripDTOS = tripMapper.tripsToTripDTOs(trips);
        return tripDTOS;
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

    public TripRegister createTripRegister2(TripVehicleDTO tripVehicleDTO) {
        TripRegister tripRegister = tripMapper.tripDTOToTrip(tripVehicleDTO.getTripDTO());
        return this.tripRegisterRepositoryInterface.save(tripRegister);
    }

    @Override
    public TripRegister updateTrip(Long id, TripDTO tripDTO) {
        Optional<TripRegister> existingTripRegister = tripRegisterRepositoryInterface.findById(id);
        if (existingTripRegister.isPresent()) {
            TripRegister updatedTripRegister = tripMapper.tripDTOToTrip(tripDTO);
            tripRegisterRepositoryInterface.save(updatedTripRegister);
            return updatedTripRegister;
        }
        return null;
    }

    @Override
    public List<TripDTO> searchUpcomingFive() {
    List<TripRegister> trips = tripRegisterRepositoryInterface.findAll(); 

    return trips.stream()
            .filter(t -> t.getDate().isAfter(LocalDate.now()))
            .sorted(Comparator.comparing(TripRegister::getDate))
            .limit(5)
            .map(t -> new TripDTO(t.getId(), t.getOriginUbication(), t.getDestinationUbication(), t.getDate(), tripMapper.tripToTripDTO(t).getDriverId(), tripMapper.tripToTripDTO(t).getVehicleId())
            )
            .collect(Collectors.toList());
    }


    

}
