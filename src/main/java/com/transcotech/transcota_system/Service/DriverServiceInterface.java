package com.transcotech.transcota_system.Service;

import java.util.List;

import com.transcotech.transcota_system.model.User;

public interface DriverServiceInterface {

    public List<User> findAll();
    public User searchId(Integer id);
    public void deleteUser(Integer id);
    public User createDriver(User user);

}
