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
    private Role role;

    public User(){

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
