package com.transcotech.transcota_system.mapper;

import com.transcotech.transcota_system.model.TripRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.transcotech.transcota_system.dto.TripDTO;

@Mapper(uses = {VehicleMapper.class, UserMapper.class})
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mappings({
        @Mapping(source = "vehicle", target = "vehicleDTO"),
        @Mapping(source = "driver", target = "driverDTO"),
        @Mapping(source = "tripId", target = "tripId"),
        @Mapping(source = "origin", target = "origin"),
        @Mapping(source = "destination", target = "destination"),
        @Mapping(source = "date", target = "date")
    })
    TripDTO tripToTripDTO(TripRegister trip);
    @Mappings({
        @Mapping(source = "vehicleDTO", target = "vehicle"),
        @Mapping(source = "driverDTO", target = "driver"),
        @Mapping(source = "tripId", target = "tripId"),
        @Mapping(source = "origin", target = "origin"),
        @Mapping(source = "destination", target = "destination"),
        @Mapping(source = "date", target = "date")
    })
    TripRegister tripDTOToTrip(TripDTO tripDTO);
}

