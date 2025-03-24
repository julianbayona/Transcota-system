package com.transcotech.transcota_system.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.Service.VehicleServiceInterface;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.PasswordEncoderMapper;
import com.transcotech.transcota_system.mapper.UserMapper;
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
    private VehicleServiceInterface vehicleRepository;

    @Mock
    private PasswordEncoderMapper passwordEncoderMapper;

    private User driver1;
    private User driver2;
    private List<User> driverList;

    private ArrayList<TripRegister> tripRegisters;

    private ArrayList<UserDTO> userDTOs;

    private List<VehicleDTO> vehicles;

    @BeforeEach
    void setUp() {
        driver1 = new User();
        driver1.setName("Juan Pérez");
        driver1.setEmail("juan.perez@ejemplo.com");
        driver1.setPersonId(1L);

        driver2 = new User();
        driver2.setName("María García");
        driver2.setEmail("maria.garcia@ejemplo.com");
        driver2.setPersonId(2L);

        driverList = Arrays.asList(driver1, driver2);

        userDTOs = new ArrayList<UserDTO>();

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setPersonId(1L);
        userDTO1.setName("Juan Pérez");
        userDTO1.setEmail("juan.perez@ejemplo.com");
        userDTO1.setPassword("encodedPassword1");
        userDTOs.add(userDTO1);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setPersonId(2L);
        userDTO2.setName("María García");
        userDTO2.setEmail("maria.garcia@ejemplo.com");
        userDTO2.setPassword("encodedPassword2");
        userDTOs.add(userDTO2);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1L);

        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicleId(1L);
        
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleId(2L);
        
        VehicleDTO vehicleDTO2 = new VehicleDTO();
        vehicleDTO2.setVehicleId(2L);

        vehicles = Arrays.asList(vehicleDTO, vehicleDTO2);

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
    public void findAllTest() {
        when(driverRepository.findAll()).thenReturn(driverList);
        when(userMapper.usersToUserDTOs(driverList)).thenReturn(userDTOs);

        List<UserDTO> result = driverService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Juan Pérez", result.get(0).getName());
        assertEquals("María García", result.get(1).getName());
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

    @Test
    public void createDriverTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPersonId(3L);
        userDTO.setName("Fernando Gonzalez");
        userDTO.setEmail("fernando.gonzalez@ejemplo.com");
        userDTO.setPassword("encodedPassword3");

        User userToReturn = new User();
        userToReturn.setPersonId(3L);
        userToReturn.setName("Fernando Gonzalez");
        userToReturn.setEmail("fernando.gonzalez@ejemplo.com");
        userToReturn.setPassword("encodedPassword3");

        when(userMapper.userDTOToUser(userDTO)).thenReturn(userToReturn);
        when(driverRepository.save(any(User.class))).thenReturn(userToReturn);

        User result = driverService.createDriver(userDTO);

        assertNotNull(result);
        verify(driverRepository, times(1)).save(any(User.class));
    };

    @Test
    public void updateDriverTest() {
        Long idToUpdate = 2L;

        User driverUpdated = new User();
        driverUpdated.setName("Harold García");
        driverUpdated.setEmail("harold.garcia@ejemplo.com");
        driverUpdated.setPersonId(2L);

        UserDTO driverUpdatedDTO = new UserDTO();
        driverUpdatedDTO.setPersonId(2L);
        driverUpdatedDTO.setName("Harold García");
        driverUpdatedDTO.setEmail("harold.garcia@ejemplo.com");
        driverUpdatedDTO.setPassword("encodedPassword2");

        when(driverRepository.findById(idToUpdate)).thenReturn(Optional.of(driver2));
        when(userMapper.userDTOToUser(driverUpdatedDTO)).thenReturn(driverUpdated);
        when(driverRepository.save(any(User.class))).thenReturn(driverUpdated);

        User result = driverService.updateDriver(idToUpdate, driverUpdatedDTO);

        assertNotNull(result);
        assertTrue(driverUpdated.getName().equals(result.getName()));
        assertTrue(driverUpdated.getEmail().equals(result.getEmail()));
        verify(driverRepository, times(1)).findById(idToUpdate);
        verify(driverRepository, times(1)).save(any(User.class));
        verify(userMapper, times(1)).userDTOToUser(driverUpdatedDTO);
    };

    @Test
    public void getVehiclesAssignedDriverTest() {
        Long idToSearch = 2L;

        when(tripRegisterRepository.findAll()).thenReturn(tripRegisters);
        when(vehicleRepository.searchId(anyLong())).thenAnswer(invocation -> {
            long id = invocation.getArgument(0);
            return vehicles.stream()
                    .filter(vehicle -> vehicle.getVehicleId() == id)
                    .findFirst()
                    .orElse(null);
        });

        List<VehicleDTO> result = driverService.getVehiclesAssignedDriver(idToSearch);

        assertNotNull(result);
        assertTrue(result.stream().anyMatch(p -> p.getVehicleId() == 1L));
        assertTrue(result.stream().anyMatch(p -> p.getVehicleId() == 2L));
        assertEquals(2, result.size());
    }
}