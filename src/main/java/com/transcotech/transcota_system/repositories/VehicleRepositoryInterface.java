package com.transcotech.transcota_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.transcotech.transcota_system.model.Vehicle;

@Repository
public interface VehicleRepositoryInterface extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByPlate(String plate);
}
