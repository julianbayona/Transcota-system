package com.transcotech.transcota_system.model;

public class User extends Person{

    private Role roles;

    public User(Role role, Long id, String name, String email){
        this.roles = role;
        super.setId(id);
        super.setName(name);
        super.setEmail(email);
    }

}
