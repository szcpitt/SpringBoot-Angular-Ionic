package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String gender;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }
}
