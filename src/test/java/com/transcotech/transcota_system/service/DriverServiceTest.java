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
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.mapper.UserMapper;
import com.transcotech.transcota_system.model.Role;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {
    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepositoryInterface driverRepository;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Usa una implementación real

    @Mock
    private UserMapper userMapper; // Simula el mapeador

    private User driver1;
    private User driver2;
    private UserDTO driverDTO1;
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

        driverDTO1 = new UserDTO();
        driverDTO1.setPersonId(1L);
        driverDTO1.setName("John Doe");
        driverDTO1.setEmail("john.doe@example.com");

        driverList = Arrays.asList(driver1, driver2);

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
    void deleteUserTest() {

        Long idToDelete = 1L;
        doNothing().when(driverRepository).deleteById(idToDelete);

        driverService.deleteUser(idToDelete);

        verify(driverRepository, times(1)).deleteById(idToDelete);
    }
}