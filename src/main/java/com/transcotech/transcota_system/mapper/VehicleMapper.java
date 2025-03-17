package com.transcotech.transcota_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.model.Vehicle;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);

    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);

}
