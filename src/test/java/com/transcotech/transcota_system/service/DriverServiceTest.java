package com.transcotech.transcota_system.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.dto.UserDTO;
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

        // Configura otros campos necesarios para User

        driver2 = new User();

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

    /*
     * @Test
     * void searchIdWhenDriverExists() {
     * UserDTO driverDTO = new UserDTO();
     * driverDTO.setName("Juan Pérez");
     * driverDTO.setEmail("juan.perez@ejemplo.com");
     * 
     * when(driverRepository.findById(1L)).thenReturn(Optional.of(driver1));
     * when(userMapper.toDTO(driver1)).thenReturn(driverDTO); // Simula la
     * conversión de User a UserDTO
     * 
     * UserDTO result = driverService.searchId(1L); // Debe ser UserDTO en lugar de
     * User
     * 
     * assertNotNull(result);
     * assertEquals("Juan Pérez", result.getName());
     * assertEquals("juan.perez@ejemplo.com", result.getEmail());
     * verify(driverRepository, times(1)).findById(1L);
     * }
     * 
     * @Test
     * void searchIdWhenDriverDoesNotExist() {
     * 
     * when(driverRepository.findById(3L)).thenReturn(Optional.empty());
     * 
     * 
     * User result = driverService.searchId(3L);
     * 
     * 
     * assertNull(result);
     * verify(driverRepository, times(1)).findById(3L);
     * }
     * 
     */
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

        when(driverRepository.save(any(User.class))).thenReturn(newDriver);

        User result = driverService.createDriver(newDriver);

        assertNotNull(result);
        assertEquals("Pedro López", result.getName());
        assertEquals("pedro.lopez@ejemplo.com", result.getEmail());
        verify(driverRepository, times(1)).save(newDriver);
    }
}