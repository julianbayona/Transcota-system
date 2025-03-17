package com.transcotech.transcota_system.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class TripRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "personId")
    private User driver;

    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;

    private String destinationUbication;

    private String originUbication;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Enumerated(EnumType.STRING)    
    private StatusTrip status;


    public TripRegister(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDestinationUbication() {
        return destinationUbication;
    }

    public void setDestinationUbication(String destinationUbication) {
        this.destinationUbication = destinationUbication;
    }

    public String getOriginUbication() {
        return originUbication;
    }

    public void setOriginUbication(String originUbication) {
        this.originUbication = originUbication;
    }

    public StatusTrip getStatus() {
        return status;
    }

    public void setStatus(StatusTrip status) {
        this.status = status;
    }

    
}
