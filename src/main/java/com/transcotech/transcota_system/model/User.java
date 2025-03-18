package com.transcotech.transcota_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="userSystem")
public class User extends Person {

    
    @Column(name= "role")
    private TypeVehicle role;

    public User(){

    }

    public TypeVehicle getRole() {
        return role;
    }

    public void setRole(TypeVehicle role) {
        this.role = role;
    }
}
