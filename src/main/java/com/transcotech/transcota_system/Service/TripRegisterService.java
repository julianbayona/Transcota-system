package com.transcotech.transcota_system.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.TripMapper;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.model.TripVehicleDTO;
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
    public List<TripDTO> findAll() {
        List<TripRegister> trips = tripRegisterRepositoryInterface.findAll();
        List<TripDTO> tripDTOS = tripMapper.tripsToTripDTOs(trips);
        /*TripRegister driver = new TripRegister();
        driver.setTripRegisterId(1); // ID del conductor
        driver.setName("Juan Pérez"); // Nombre del conductor
        driver.setRole(Role.DRIVER);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1); // ID del vehículo
        vehicle.setPlate("ABC-123"); // Placa del vehículo
        vehicle.setModel("Toyota Hilux"); // Modelo del vehículo
        vehicle.setYear(2020); // Año del vehículo
        vehicle.setType(TypeVehicle.LOADING);

        TripRegister tripRegister = new TripRegister();
        tripRegister.setId(1001L); // ID del registro de viaje
        tripRegister.setDriverId(driver); // Conductor asignado
        tripRegister.setVehicleId(vehicle); // Vehículo asignado
        tripRegister.setOriginUbication("Ciudad de México"); // Ubicación de origen
        tripRegister.setDestinationUbication("Guadalajara"); // Ubicación de destino
        tripRegister.setDate(LocalDate.of(2023, 10, 15));
        List<TripDTO> tripDTOS = new ArrayList<TripDTO>();
        tripDTOS.add(tripMapper.tripToTripDTO(tripRegister));*/
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
        System.out.println("VIAJE actualizado");
        return null;
    }
    

}
