package com.transcotech.transcota_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return tripId != null && tripId.equals(trip.tripId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
