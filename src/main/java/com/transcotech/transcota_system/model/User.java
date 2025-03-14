package com.transcotech.transcota_system.model;

import java.util.ArrayList;
import java.util.List;

public class User extends Person{

    private List<Role> roles;

    public User(Role role,int id, String name, String email){
        roles =  new ArrayList<Role>();
        roles.add(role);
        super.setId(id);
        super.setName(name);
        super.setEmail(email);
    }

}
