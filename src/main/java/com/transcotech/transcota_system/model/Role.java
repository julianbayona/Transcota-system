package com.transcotech.transcota_system.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    public Role(){
    }

    public Integer getRoleId() {
        return roleId;
    }



    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
