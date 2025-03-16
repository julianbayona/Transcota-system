package com.transcotech.transcota_system.model;

import jakarta.persistence.*;


@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "personId")
    private Long personId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public Person(){

    }

    public Person(Long personId, String name, String email) {
        this.personId = personId;
        this.name = name;
        this.email = email;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
