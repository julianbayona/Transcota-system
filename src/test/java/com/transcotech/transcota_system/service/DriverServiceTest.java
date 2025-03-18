package com.transcotech.transcota_system.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    private DriverRepositoryInterface driverRepository;

    @InjectMocks
    private DriverService driverService;

    private User driver1;
    private User driver2;
    private List<User> driverList;

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

        driverList = Arrays.asList(driver1, driver2);
    }

    @Test
    void findAllTest() {
        
        when(driverRepository.findAll()).thenReturn(driverList);

        
        List<User> result = driverService.findAll();

        
        assertEquals(2, result.size());
        assertEquals(driverList, result);
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    void deleteUserTest() {
        
        Long idToDelete = 1L;
        doNothing().when(driverRepository).deleteById(idToDelete);

        
        driverService.deleteUser(idToDelete);
        
        
        verify(driverRepository, times(1)).deleteById(idToDelete);
    }
    
    @Test
    void createDriverTest() {
        
        User newDriver = new User();
        newDriver.setName("Pedro López");
        newDriver.setEmail("pedro.lopez@ejemplo.com");
        newDriver.setPersonId(12345L);
        
        when(driverRepository.save(any(User.class))).thenReturn(newDriver);

        
        User result = driverService.createDriver(newDriver);
        
        
        assertNotNull(result);
        assertEquals("Pedro López", result.getName());
        assertEquals("pedro.lopez@ejemplo.com", result.getEmail());
        verify(driverRepository, times(1)).save(newDriver);
    }
}