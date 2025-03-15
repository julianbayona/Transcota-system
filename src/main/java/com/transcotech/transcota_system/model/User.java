package com.transcotech.transcota_system.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("user")
public class User extends Person {

    @OneToOne
    @JoinColumn(name = "role_id") // Clave foránea en la tabla User
    private Role role;

    public User(Role role, Integer id, String name, String email) {
        this.role = role;
        super.setId(id);
        super.setName(name);
        super.setEmail(email);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
