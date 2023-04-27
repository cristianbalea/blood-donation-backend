package com.example.blooddonation.domain.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Doctor extends User {
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    public Doctor(String cnp, String email, String password, String lastName, String firstName, Location location) {
        super(cnp, firstName, lastName, email, password, Role.DOCTOR);
        this.location = location;
    }

    public Doctor() {
        this.setRole(Role.DOCTOR);
    }
}
