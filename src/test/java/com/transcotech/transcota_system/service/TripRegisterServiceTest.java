package com.transcotech.transcota_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transcotech.transcota_system.Service.TripRegisterService;
import com.transcotech.transcota_system.dto.TripDTO;
import com.transcotech.transcota_system.mapper.TripMapper;
import com.transcotech.transcota_system.model.TripRegister;
import com.transcotech.transcota_system.model.User;
import com.transcotech.transcota_system.model.Vehicle;
import com.transcotech.transcota_system.repositories.TripRegisterRepositoryInterface;

@ExtendWith(MockitoExtension.class)
public class TripRegisterServiceTest {

    @Mock
    private TripRegisterRepositoryInterface tripRepository;

    @InjectMocks
    private TripRegisterService tripService;

    private final TripMapper tripMapper = TripMapper.INSTANCE;

    private TripRegister trip1;
    private TripRegister trip2;
    private List<TripRegister> trips;

    private User driver1;
    private User driver2;
    private User driver3;

    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private Vehicle vehicle3;

    @BeforeEach
    void setUp() {
        driver1 = new User();
        driver1.setUserId(1L);
        driver2 = new User();
        driver2.setUserId(2L);
        driver3 = new User();
        driver3.setUserId(3L);

        vehicle1 = new Vehicle();
        vehicle1.setVehicleId(1L);
        vehicle2 = new Vehicle();
        vehicle2.setVehicleId(2L);
        vehicle3 = new Vehicle();
        vehicle3.setVehicleId(3L);

        trip1 = new TripRegister();
        trip1.setId(1L);
        trip1.setDate(LocalDate.of(2025, 8, 29));
        trip1.setOriginUbication("Tunja");
        trip1.setDestinationUbication("Bogotá");
        trip1.setDriverId(driver1);
        trip1.setVehicleId(vehicle1);

        trip2 = new TripRegister();
        trip2.setId(2L);
        trip2.setDate(LocalDate.of(2025, 4, 20));
        trip2.setOriginUbication("Barranquilla");
        trip2.setDestinationUbication("Sogamoso");
        trip2.setDriverId(driver2);
        trip2.setVehicleId(vehicle2);

        trips = Arrays.asList(trip1, trip2);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest() {

        when(tripRepository.findAll()).thenReturn(trips);

        List<TripDTO> result = tripService.findAll();

        assertEquals(2, result.size());
        assertTrue(trips.get(0).getDate().isEqual(result.get(0).getDate()));
        assertEquals(trips.get(0).getId(), result.get(0).getTripId());
        assertTrue(trips.get(0).getDestinationUbication().equals(result.get(0).getDestination()));
        assertTrue(trips.get(0).getOriginUbication().equals(result.get(0).getOrigin()));
        System.out.println(result.get(1).toString());
        assertTrue(trips.get(1).getDate().isEqual(result.get(1).getDate()));
        assertEquals(trips.get(1).getId(), result.get(1).getTripId());
        assertTrue(trips.get(1).getDestinationUbication().equals(result.get(1).getDestination()));
        assertTrue(trips.get(1).getOriginUbication().equals(result.get(1).getOrigin()));
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    public void searchTripRegisterByIdTest() {
        Long idToSearch = 1L;

        when(tripRepository.findById(idToSearch)).thenReturn(Optional.of(trip1));

        TripDTO result = tripService.searchTripRegisterById(idToSearch);

        assertEquals(trip1.getId(), result.getTripId());
        assertTrue(trip1.getDate().isEqual(result.getDate()));
        assertTrue(trip1.getOriginUbication().equals(result.getOrigin()));
        assertTrue(trip1.getDestinationUbication().equals(result.getDestination()));
        verify(tripRepository, times(1)).findById(idToSearch);
    }

    @Test
    public void deleteTripRegisterTest() {
        Long idToDelete = 1L;

        tripService.deleteTripRegister(idToDelete);

        verify(tripRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void createTripRegisterTest() {
        TripDTO tripDTO = tripMapper.tripToTripDTO(trip1);

        when(tripRepository.save(any(TripRegister.class))).thenReturn(trip1);

        tripService.createTripRegister(tripDTO);

        verify(tripRepository, times(1)).save(any(TripRegister.class));
    }

    @Test
    public void updateTripTest() {
        Long idToUpdate = 1L;

        TripRegister tripToUpdate = new TripRegister();
        tripToUpdate.setId(idToUpdate);
        tripToUpdate.setDate(LocalDate.now());
        tripToUpdate.setDestinationUbication("Tibasosa");
        tripToUpdate.setOriginUbication("Tunja");
        tripToUpdate.setDriverId(driver1);
        tripToUpdate.setVehicleId(vehicle1);

        TripDTO tripDTO = tripMapper.tripToTripDTO(tripToUpdate);

        when(tripRepository.findById(idToUpdate)).thenReturn(Optional.of(trip1));
        when(tripRepository.save(any(TripRegister.class))).thenReturn(tripToUpdate);

        TripRegister result = tripService.updateTrip(idToUpdate, tripDTO);

        assertNotNull(result);
        assertEquals(trip1.getId(), result.getId());
        assertFalse(trip1.getDate().isEqual(result.getDate()));
        assertTrue(trip1.getOriginUbication().equals(result.getOriginUbication()));
        assertFalse(trip1.getDestinationUbication().equals(result.getDestinationUbication()));
        verify(tripRepository, times(1)).findById(idToUpdate);
        verify(tripRepository, times(1)).save(any(TripRegister.class));
    }

    @Test
    public void searchUpcomingFiveTest() {

        
        TripRegister trip3 = new TripRegister();
        trip3.setId(3L);
        trip3.setDate(LocalDate.of(2025, 9, 1));
        trip3.setOriginUbication("Medellín");
        trip3.setDestinationUbication("Cali");
        trip3.setDriverId(driver2);
        trip3.setVehicleId(vehicle1);

        TripRegister trip4 = new TripRegister();
        trip4.setId(4L);
        trip4.setDate(LocalDate.of(2025, 9, 2));
        trip4.setOriginUbication("Cali");
        trip4.setDestinationUbication("Pasto");
        trip4.setDriverId(driver2);
        trip4.setVehicleId(vehicle2);

        TripRegister trip5 = new TripRegister();
        trip5.setId(5L);
        trip5.setDate(LocalDate.of(2025, 9, 3));
        trip5.setOriginUbication("Pasto");
        trip5.setDestinationUbication("Ipiales");
        trip5.setDriverId(driver1);
        trip5.setVehicleId(vehicle2);

        TripRegister trip6 = new TripRegister();
        trip6.setId(6L);
        trip6.setDate(LocalDate.of(2025, 9, 4));
        trip6.setOriginUbication("Ipiales");
        trip6.setDestinationUbication("Tulcán");
        trip6.setDriverId(driver3);
        trip6.setVehicleId(vehicle3);

        TripRegister trip7 = new TripRegister();
        trip7.setId(7L);
        trip7.setDate(LocalDate.of(2025, 9, 5));
        trip7.setOriginUbication("Tulcán");
        trip7.setDestinationUbication("Quito");
        trip7.setDriverId(driver2);
        trip7.setVehicleId(vehicle1);

        TripRegister trip8 = new TripRegister();
        trip8.setId(8L);
        trip8.setDate(LocalDate.of(2025, 9, 6));
        trip8.setOriginUbication("Quito");
        trip8.setDestinationUbication("Guayaquil");
        trip8.setDriverId(driver3);
        trip8.setVehicleId(vehicle2);

        trips = Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6, trip7, trip8);

        when(tripRepository.findAll()).thenReturn(trips);
        
        List<TripDTO> result = tripService.searchUpcomingFive();

        assertNotNull(result);
        assertEquals(2L, result.get(0).getTripId());
        assertEquals(1L, result.get(1).getTripId());
        assertEquals(3L, result.get(2).getTripId());
        assertEquals(4L, result.get(3).getTripId());
        assertEquals(5L, result.get(4).getTripId());
        verify(tripRepository, times(1)).findAll();
    }

}
