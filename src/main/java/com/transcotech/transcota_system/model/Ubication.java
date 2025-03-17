package com.transcotech.transcota_system.model;


public class Ubication {

    private Long id;

    private String name;

    private Ubication parentUbication;

    public Ubication(){

    }

    public Ubication(Long id, String name, Ubication ubication) {
        this.id = id;
        this.name = name;
        this.parentUbication = ubication;
    }

    public Ubication(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ubication getUbication() {
        return parentUbication;
    }

    public void setUbication(Ubication ubication) {
        this.parentUbication = ubication;
    }
}

