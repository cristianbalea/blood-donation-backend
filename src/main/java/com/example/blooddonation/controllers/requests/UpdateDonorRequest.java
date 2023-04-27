package com.example.blooddonation.controllers.requests;

import com.example.blooddonation.domain.dtos.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UpdateDonorRequest {
    private UUID id;
    private String cnp;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String area;
    private String bloodType;
}
