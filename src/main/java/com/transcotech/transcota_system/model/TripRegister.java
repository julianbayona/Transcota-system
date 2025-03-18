package com.transcotech.transcota_system.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TripRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personId")
    private User driverId;

    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicleId;

    @Column(name="destino")
    private String destinationUbication;

    @Column(name="origen")
    private String originUbication;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;


    public TripRegister(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getDriverId() {
        return driverId;
    }

    public void setDriverId(User personId) {
        this.driverId = personId;
    }

    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
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
    
}
