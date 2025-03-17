package com.transcotech.transcota_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transcotech.transcota_system.model.TripRegister;

@Repository
public interface TripRegisterRepositoryInterface extends JpaRepository<TripRegister, Long> {
}
