package com.transcotech.transcota_system.repositories;

import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRegisterRepositoryInterface extends JpaRepository<TripRegister, Long> {
}
