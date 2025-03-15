package com.transcotech.transcota_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transcotech.transcota_system.model.User;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
public interface DriverRepositoryInterface extends JpaRepository<User, Long>{
=======
@Repository
public interface DriverRepositoryInterface extends JpaRepository<User, Integer>{
>>>>>>> d1d179cc6e8dea15eeb16178702a75db6ea92dad

}
