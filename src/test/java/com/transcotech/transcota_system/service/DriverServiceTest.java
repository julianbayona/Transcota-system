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
import org.mockito.junit.jupiter.MockitoExtension;

import com.transcotech.transcota_system.Service.DriverService;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.mapper.UserMapper;
import com.transcotech.transcota_system.model.Role;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    private DriverRepositoryInterface driverRepository;

    @InjectMocks
    private DriverService driverService;

    private final UserMapper userMapper = UserMapper.INSTANCE;

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

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchId_WhenUserExists() {
        UserDTO expectedUserDTO = userMapper.userToUserDTO(driver1);
        Long userId = 1L;

        when(driverRepository.findById(userId)).thenReturn(Optional.of(driver1));

        UserDTO result = driverService.searchId(userId);

        assertEquals(expectedUserDTO, result);
        verify(driverRepository, times(1)).findById(userId); // Verifica que se llame a findById una vez
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
    void findAllTest() {

        when(driverRepository.findAll()).thenReturn(driverList);

        List<UserDTO> result = driverService.findAll();

        List<UserDTO> expectedUserDTOs = userMapper.usersToUserDTOs(driverList);

        assertEquals(2, result.size());
        assertEquals(expectedUserDTOs, result);
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
        User user =  new User();
        user.setPersonId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        UserDTO userDTO = userMapper.userToUserDTO(user);

        when(driverRepository.save(any(User.class))).thenReturn(user);

        User result = driverService.createDriver(userDTO);

        assertEquals(user, result);
        verify(driverRepository, times(1)).save(any(User.class));
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }
}