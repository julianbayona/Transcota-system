package com.transcotech.transcota_system.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.VehicleMapper;
import com.transcotech.transcota_system.model.TypeVehicle;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.VehicleRepositoryInterface;

//@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
/*
    @Mock
    private VehicleRepositoryInterface vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private final VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private List<Vehicle> vehicleList;

    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        vehicle1 = new Vehicle();
        vehicle1.setVehicleId(1L);
        vehicle1.setPlate("ABC123");
        vehicle1.setModel("Toyota Corolla");
        vehicle1.setYear(2022);
        vehicle1.setType(TypeVehicle.LOADING);

        vehicle2 = new Vehicle();
        vehicle2.setVehicleId(2L);
        vehicle2.setPlate("XYZ789");
        vehicle2.setModel("Honda Civic");
        vehicle2.setYear(2023);
        vehicle2.setType(TypeVehicle.PASSENGER);

        vehicleList = Arrays.asList(vehicle1, vehicle2);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {
        when(vehicleRepository.findAll()).thenReturn(vehicleList);

        List<Vehicle> result = vehicleMapper.vehicleDTOsToVehicles(vehicleService.findAll());

        assertEquals(2, result.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void searchIdWhenVehicleExists() {
        Long vehicleId = 1L;

        VehicleDTO expectedVehicleDTO = vehicleMapper.vehicleToVehicleDTO(vehicle1);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle1));

        VehicleDTO result = vehicleService.searchId(vehicleId);

        assertEquals(expectedVehicleDTO, result);
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    void searchIdWhenVehicleDoesNotExist() {

        Long vehicleId = 99L;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        VehicleDTO result = vehicleService.searchId(vehicleId);

        assertNull(result);
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    void deleteVehicleWhenVehicleExists() {

        when(vehicleRepository.existsById(1L)).thenReturn(true);
        doNothing().when(vehicleRepository).deleteById(1L);

        boolean result = vehicleService.deleteVehicle(1L);

        assertTrue(result);
        verify(vehicleRepository, times(1)).existsById(1L);
        verify(vehicleRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteVehicleWhenVehicleDoesNotExistFalse() {

        when(vehicleRepository.existsById(3L)).thenReturn(false);

        boolean result = vehicleService.deleteVehicle(3L);

        assertFalse(result);
        verify(vehicleRepository, times(1)).existsById(3L);
        verify(vehicleRepository, never()).deleteById(any());
    }

    @Test
    void createVehicleWhenVehicleDoesNotExistTrue() {
        VehicleDTO vehicleDTO = new VehicleDTO(1L, "ABC123", "Model1", 2020, TypeVehicle.PASSENGER);
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);

        when(vehicleRepository.findByPlate(vehicle.getPlate())).thenReturn(Optional.empty());
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        boolean result = vehicleService.createVehicle(vehicleDTO);

        assertTrue(result);
        verify(vehicleRepository, times(1)).findByPlate(vehicle.getPlate());
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    void createVehicleWhenVehicleExistsFalse() {
        VehicleDTO vehicleDTO = new VehicleDTO(1L, "ABC123", "Model1", 2020, TypeVehicle.LOADING);
        Vehicle newVehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);

        when(vehicleRepository.findByPlate(newVehicle.getPlate())).thenReturn(Optional.of(newVehicle));

        boolean result = vehicleService.createVehicle(vehicleDTO);

        assertFalse(result);
        verify(vehicleRepository, times(1)).findByPlate(newVehicle.getPlate());
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    void updateVehicleWhenVehicleExistsTrue() {

        Long vehicleId = vehicle1.getVehicleId();
        VehicleDTO vehicleDTO = new VehicleDTO(vehicleId, "XYZ789", "Model2", 2021, TypeVehicle.LOADING);
        Vehicle updatedVehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle1));
        when(vehicleRepository.save(vehicle1)).thenReturn(vehicle1);

        String result = vehicleService.updateVehicle(vehicleId, vehicleDTO);

        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(vehicleRepository, times(1)).save(vehicle1);
        assertEquals("actualizado", result);
        assertEquals(updatedVehicle.getPlate(), vehicle1.getPlate());
        assertEquals(updatedVehicle.getModel(), vehicle1.getModel());
        assertEquals(updatedVehicle.getYear(), vehicle1.getYear());
        assertEquals(updatedVehicle.getType(), vehicle1.getType());
    }

    @Test
    void updateVehicleWhenVehicleDoesNotExistFalse() {
        Long vehicleId = 99L;
        VehicleDTO vehicleDTO = new VehicleDTO(vehicleId, "XYZ789", "Model2", 2021, TypeVehicle.PASSENGER);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        String result = vehicleService.updateVehicle(vehicleId, vehicleDTO);

        assertEquals("vehiculoInexistente", result);
        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(vehicleRepository, never()).save(any());
    }

}