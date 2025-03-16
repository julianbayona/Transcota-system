package com.transcotech.transcota_system.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Role {

    public Role(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }



    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
