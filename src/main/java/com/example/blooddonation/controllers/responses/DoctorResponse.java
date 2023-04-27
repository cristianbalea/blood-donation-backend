package com.example.blooddonation.controllers.responses;

import com.example.blooddonation.domain.dtos.Doctor;
import com.example.blooddonation.domain.dtos.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class DoctorResponse extends BaseResponse {
    private UUID id;
    private String cnp;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private UUID locationId;

    public DoctorResponse(Doctor doctor) {
        this.id = doctor.getId();
        this.cnp = doctor.getCnp();
        this.email = doctor.getEmail();
        this.password = doctor.getPassword();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.role = doctor.getRole();
        this.locationId = doctor.getLocation().getId();
    }
}
