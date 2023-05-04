package com.example.blooddonation.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DonorRegisterRequest {
    private String cnp;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String area;
    private String bloodType;
    private String phoneNumber;
}
