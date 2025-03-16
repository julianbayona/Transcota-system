package com.transcotech.transcota_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.model.Trip;

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
    TripDTO tripToTripDTO(Trip trip);
    @Mappings({
        @Mapping(source = "vehicleDTO", target = "vehicle"),
        @Mapping(source = "driverDTO", target = "driver"),
        @Mapping(source = "tripId", target = "tripId"),
        @Mapping(source = "origin", target = "origin"),
        @Mapping(source = "destination", target = "destination"),
        @Mapping(source = "date", target = "date")
    })
    Trip tripDTOToTrip(TripDTO tripDTO);
}

