package com.example.blooddonation.domain.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Donor extends User {
    @Column
    private String area;
    @Column
    private String bloodType;

    public Donor(String cnp, String firstName, String lastName, String email, String password, String area, String bloodType) {
        super(cnp, firstName, lastName, email, password, Role.DONOR);
        this.area = area;
        this.bloodType = bloodType;
    }

    public Donor() {
        this.setRole(Role.DONOR);
    }
}
