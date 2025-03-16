package com.transcotech.transcota_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.mapper.TripMapper;
import com.transcotech.transcota_system.mapper.UserMapper;
import com.transcotech.transcota_system.mapper.VehicleMapper;
import com.transcotech.transcota_system.model.DriverRole;
import com.transcotech.transcota_system.model.LoadingVehicle;
import com.transcotech.transcota_system.model.Trip;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.model.Vehicle;

@SpringBootApplication
public class TranscotaSystemApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TranscotaSystemApplication.class, args);
	}

}
