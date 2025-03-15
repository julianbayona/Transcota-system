package com.transcotech.transcota_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ubication")
public class Ubication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
    @JoinColumn(name = "parent_id")
    private Ubication parentUbication;

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

