package com.transcotech.transcota_system.model;


import java.time.LocalDate;


public class TripRegister {


    private Long id;

    private User driver;

    private Vehicle vehicle;

    private Ubication destinationUbication;

    private Ubication originUbication;

    private LocalDate date;

    private String status;


    public TripRegister(){

    }

    public TripRegister(Long id, User driver, Vehicle vehicle, LocalDate date, String status, Ubication destinationUbication, Ubication originUbication) {
        this.id = id;
        this.driver = driver;
        this.vehicle = vehicle;
        this.date = date;
        this.status = status;
        this.destinationUbication = destinationUbication;
        this.originUbication = originUbication;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ubication getDestinationUbication() {
        return destinationUbication;
    }

    public void setDestinationUbication(Ubication destinationUbication) {
        this.destinationUbication = destinationUbication;
    }

    public Ubication getOriginUbication() {
        return originUbication;
    }

    public void setOriginUbication(Ubication originUbication) {
        this.originUbication = originUbication;
    }
}
