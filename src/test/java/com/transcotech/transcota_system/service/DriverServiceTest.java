package com.transcotech.transcota_system.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.mapper.PasswordEncoderMapper;
import com.transcotech.transcota_system.mapper.UserMapper;
import com.transcotech.transcota_system.model.Role;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;
import com.transcotech.transcota_system.repositories.TripRegisterRepositoryInterface;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {
    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepositoryInterface driverRepository;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mock
    private UserMapper userMapper;

    @Mock
    private TripRegisterRepositoryInterface tripRegisterRepository;

    @Mock
private PasswordEncoderMapper passwordEncoderMapper;


    private User driver1;
    private User driver2;
    private UserDTO driverDTO1;
    private List<User> driverList;

    private ArrayList<TripRegister> tripRegisters;

    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        driver1 = new User();
        driver1.setName("Juan perez");
        driver1.setEmail("juan.perez@ejemplo.com");
        driver1.setPersonId(1L);

        // Configura otros campos necesarios para User

        driver2 = new User();
        driver2.setName("María García");
        driver2.setEmail("maria.garcia@ejemplo.com");
        driver2.setPersonId(2L);

        driverDTO1 = new UserDTO();
        driverDTO1.setPersonId(1L);
        driverDTO1.setName("John Doe");
        driverDTO1.setEmail("john.doe@example.com");

        driverList = Arrays.asList(driver1, driver2);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1L);

        Vehicle vehicle2 = new Vehicle();
        vehicle.setVehicleId(2L);

        TripRegister trip1 = new TripRegister();
        trip1.setId(1L);
        trip1.setDate(LocalDate.now());
        trip1.setOriginUbication("Tunja");
        trip1.setDestinationUbication("Barranquilla");
        trip1.setDriverId(driver2);
        trip1.setVehicleId(vehicle);

        TripRegister trip2 = new TripRegister();
        trip2.setId(2L);
        trip2.setDate(LocalDate.now());
        trip2.setOriginUbication("Pasto");
        trip2.setDestinationUbication("Sogamoso");
        trip2.setDriverId(driver2);
        trip2.setVehicleId(vehicle2);

        tripRegisters = new ArrayList<>();
        tripRegisters.add(trip1);
        tripRegisters.add(trip2);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchId_WhenUserDoesNotExist() {
        Long userId = 99L;

        when(driverRepository.findById(userId)).thenReturn(Optional.empty());

        UserDTO result = driverService.searchId(userId);

        assertNull(result);
        verify(driverRepository, times(1)).findById(userId);
    }

    @Test
    void deleteUserWithoutTripsTest() {
        Long idToDelete = 1L;
        doNothing().when(driverRepository).deleteById(idToDelete);
        when(tripRegisterRepository.findAll()).thenReturn(tripRegisters);

        boolean result = driverService.deleteUser(idToDelete);

        assertTrue(result);
        verify(driverRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void deleteUserWithTripsTest() {
        Long idToDelete = 2L;
        when(tripRegisterRepository.findAll()).thenReturn(tripRegisters);

        boolean result = driverService.deleteUser(idToDelete);

        assertFalse(result);
    }
}