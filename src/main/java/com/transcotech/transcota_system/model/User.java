package com.transcotech.transcota_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name="userSystem")
public class User extends Person {

    @Enumerated(EnumType.STRING)
    @Column(name= "role")
    private String role;

    public User(){

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
