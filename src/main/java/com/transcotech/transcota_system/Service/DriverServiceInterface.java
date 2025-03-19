package com.transcotech.transcota_system.Service;

import java.util.List;

import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.model.User;

public interface DriverServiceInterface {

    public List<UserDTO> findAll();
    public UserDTO searchId(Long id);
    public void deleteUser(Long id);
    public User createDriver(UserDTO userDTO);
    public User updateDriver(Long id, UserDTO userDTO);
}
