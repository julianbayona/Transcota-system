package com.transcotech.transcota_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transcotech.transcota_system.model.User;

public interface DriverRepositoryInterface extends JpaRepository<User, Long> {
    User findByPersonId(Long personId);
}
