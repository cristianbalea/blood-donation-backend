package com.example.blooddonation.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UpdateDoctorRequest {
    private UUID id;
    private String cnp;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UUID locationId;
}
