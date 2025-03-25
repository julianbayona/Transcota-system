package com.transcotech.transcota_system.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.model.TripRegister;

@Mapper
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mappings({
        @Mapping(source = "vehicleId", target = "vehicleId"),
        @Mapping(source = "driverId", target = "driverId"),
        @Mapping(source = "id", target = "tripId"),
        @Mapping(source = "originUbication", target = "origin"),
        @Mapping(source = "destinationUbication", target = "destination"),
        @Mapping(source = "date", target = "date")
    })
    TripDTO tripToTripDTO(TripRegister trip);
    @Mappings({
        @Mapping(source = "vehicleId", target = "vehicleId"),
        @Mapping(source = "driverId", target = "driverId"),
        @Mapping(source = "tripId", target = "id"),
        @Mapping(source = "origin", target = "originUbication"),
        @Mapping(source = "destination", target = "destinationUbication"),
        @Mapping(source = "date", target = "date")
    })
    TripRegister tripDTOToTrip(TripDTO tripDTO);

    List<TripDTO> tripsToTripDTOs(List<TripRegister> trips);

    List<TripRegister> tripDTOsTotrips(List<TripDTO> tripDTOs);

}