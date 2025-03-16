package com.transcotech.transcota_system.model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "personId")
public class User extends Person {

    @OneToOne
    @JoinColumn(name = "roleId") // Clave foránea en la tabla User
    private Role role;

    public User(Role role, Long id, String name, String email) {
        this.role = role;
        super.setPersonId(id);
        super.setName(name);
        super.setEmail(email);
    }

    public User(){

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
