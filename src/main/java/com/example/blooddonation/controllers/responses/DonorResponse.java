package com.example.blooddonation.controllers.responses;

import com.example.blooddonation.domain.dtos.Donor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class DonorResponse extends BaseResponse {
    private UUID id;
    private String cnp;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String area;
    private String bloodType;

    public DonorResponse(Donor donor) {
        this.id = donor.getId();
        this.cnp = donor.getCnp();
        this.email = donor.getEmail();
        this.password = donor.getPassword();
        this.firstName = donor.getFirstName();
        this.lastName = donor.getLastName();
        this.area = donor.getArea();
        this.bloodType = donor.getBloodType();
    }
}
