package com.transcotech.transcota_system.model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "personId")
public class User extends Person {

    @OneToOne
    @JoinColumn(name = "roleId")
    private Role role;

    public User() {
    }

    public User(Long id, String name, String email, Role role) {
        super(id, name, email);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
