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
import com.transcotech.transcota_system.model.DriverRole;
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
        driver1 = new User(1L,"Juan Pérez", "juan.perez@ejemplo.com", new DriverRole());;
        
        // Configura otros campos necesarios para User
        
        driver2 = new User(2L,"María García", "maria.garcia@ejemplo.com", new DriverRole());;

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
    void searchIdWhenDriverExists() {
        
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver1));

        
        User result = driverService.searchId(1L);

        
        assertNotNull(result);
        assertEquals("Juan Pérez", result.getName());
        assertEquals("juan.perez@ejemplo.com", result.getEmail());
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    void searchIdWhenDriverDoesNotExist() {
        
        when(driverRepository.findById(3L)).thenReturn(Optional.empty());
        
        
        User result = driverService.searchId(3L);
        
        
        assertNull(result);
        verify(driverRepository, times(1)).findById(3L);
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
        
        User newDriver = new User(12345L, "Andrés", "Andres@gmail.com", new DriverRole());
        newDriver.setName("Pedro López");
        newDriver.setEmail("pedro.lopez@ejemplo.com");
        
        when(driverRepository.save(any(User.class))).thenReturn(newDriver);

        
        User result = driverService.createDriver(newDriver);
        
        
        assertNotNull(result);
        assertEquals("Pedro López", result.getName());
        assertEquals("pedro.lopez@ejemplo.com", result.getEmail());
        verify(driverRepository, times(1)).save(newDriver);
    }
}