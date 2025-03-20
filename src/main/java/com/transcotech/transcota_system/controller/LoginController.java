package com.transcotech.transcota_system.controller;

import java.sql.Driver;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.repositories.DriverRepositoryInterface;

@Controller
public class LoginController {
    private final DriverRepositoryInterface driverRepository;

    @Autowired
    public LoginController(DriverRepositoryInterface driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/login")
    public String login() {
        Optional<User> driver = driverRepository.findById(1L);
        System.out.println(driver.get().getPersonId());
        return "login";
    }
}
