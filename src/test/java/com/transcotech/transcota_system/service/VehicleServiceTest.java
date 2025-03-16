package com.transcotech.transcota_system.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transcotech.transcota_system.Service.VehicleService;
import com.transcotech.transcota_system.model.LoadingVehicle;
import com.transcotech.transcota_system.model.PassengerVehicle;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.VehicleRepositoryInterface;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepositoryInterface vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private List<Vehicle> vehicleList;

    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        vehicle1 = new PassengerVehicle("BMC-499", 15);
        vehicle1.setVehicleId(1L);
        vehicle1.setPlate("ABC123");
        vehicle1.setModel("Toyota Corolla");
        vehicle1.setYear(2022);

        vehicle2 = new PassengerVehicle("CPM-835", 12);
        vehicle2.setVehicleId(2L);
        vehicle2.setPlate("XYZ789");
        vehicle2.setModel("Honda Civic");
        vehicle2.setYear(2023);

        vehicleList = Arrays.asList(vehicle1, vehicle2);
    }

    @Test
    void findAll_ShouldReturnAllVehicles() {

        when(vehicleRepository.findAll()).thenReturn(vehicleList);

        List<Vehicle> result = vehicleService.findAll();

        assertEquals(2, result.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void searchId_WhenVehicleExists_ShouldReturnVehicle() {

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle1));

        Vehicle result = vehicleService.searchId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getVehicleId());
        assertEquals("ABC123", result.getPlate());
        verify(vehicleRepository, times(1)).findById(1L);
    }

    @Test
    void searchId_WhenVehicleDoesNotExist_ShouldReturnNull() {

        when(vehicleRepository.findById(3L)).thenReturn(Optional.empty());

        Vehicle result = vehicleService.searchId(3L);

        assertNull(result);
        verify(vehicleRepository, times(1)).findById(3L);
    }

    @Test
    void deleteVehicle_WhenVehicleExists_ShouldReturnTrue() {

        when(vehicleRepository.existsById(1L)).thenReturn(true);
        doNothing().when(vehicleRepository).deleteById(1L);

        boolean result = vehicleService.deleteVehicle(1L);

        assertTrue(result);
        verify(vehicleRepository, times(1)).existsById(1L);
        verify(vehicleRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteVehicle_WhenVehicleDoesNotExist_ShouldReturnFalse() {

        when(vehicleRepository.existsById(3L)).thenReturn(false);

        boolean result = vehicleService.deleteVehicle(3L);

        assertFalse(result);
        verify(vehicleRepository, times(1)).existsById(3L);
        verify(vehicleRepository, never()).deleteById(any());
    }

    @Test
    void createVehicle_WhenVehicleDoesNotExist_ShouldReturnTrue() {

        Vehicle newVehicle = new LoadingVehicle("GDH-398", 20);
        newVehicle.setVehicleId(3L);
        newVehicle.setPlate("DEF456");
        newVehicle.setModel("Nissan Altima");
        newVehicle.setYear(2024);

        when(vehicleRepository.existsById(3L)).thenReturn(false);
        when(vehicleRepository.save(newVehicle)).thenReturn(newVehicle);

        boolean result = vehicleService.createVehicle(newVehicle);

        assertTrue(result);
        verify(vehicleRepository, times(1)).existsById(3L);
        verify(vehicleRepository, times(1)).save(newVehicle);
    }

    @Test
    void createVehicle_WhenVehicleExists_ShouldReturnFalse() {

        when(vehicleRepository.existsById(1L)).thenReturn(true);

        boolean result = vehicleService.createVehicle(vehicle1);

        assertFalse(result);
        verify(vehicleRepository, times(1)).existsById(1L);
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    void updateVehicle_WhenVehicleExists_ShouldReturnTrue() {

        Vehicle updatedVehicle = new LoadingVehicle("amd-980", 21);
        updatedVehicle.setPlate("DEF456");
        updatedVehicle.setModel("Toyota Camry");
        updatedVehicle.setYear(2025);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle1));
        when(vehicleRepository.save(any(Vehicle.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean result = vehicleService.updateVehicle(1L, updatedVehicle);

        assertTrue(result);
        verify(vehicleRepository, times(1)).findById(1L);
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));

        Vehicle captured = vehicle1;
        assertEquals("DEF456", captured.getPlate());
        assertEquals("Toyota Camry", captured.getModel());
        assertEquals(2025, captured.getYear());
    }

    @Test
    void updateVehicle_WhenVehicleDoesNotExist_ShouldReturnFalse() {

        Vehicle updatedVehicle = new LoadingVehicle("BMC-109", 28);
        updatedVehicle.setPlate("DEF456");
        updatedVehicle.setModel("Toyota Camry");
        updatedVehicle.setYear(2025);

        when(vehicleRepository.findById(3L)).thenReturn(Optional.empty());

        boolean result = vehicleService.updateVehicle(3L, updatedVehicle);

        assertFalse(result);
        verify(vehicleRepository, times(1)).findById(3L);
        verify(vehicleRepository, never()).save(any());
    }
}