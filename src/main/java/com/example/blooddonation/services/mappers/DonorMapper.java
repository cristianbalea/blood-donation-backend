package com.example.blooddonation.services.mappers;

import com.example.blooddonation.controllers.requests.DonorRegisterRequest;
import com.example.blooddonation.controllers.requests.UpdateDonorRequest;
import com.example.blooddonation.domain.dtos.Donor;
import com.example.blooddonation.domain.dtos.Role;
import com.example.blooddonation.domain.dtos.User;
import org.springframework.stereotype.Component;

@Component
public class DonorMapper {
    public Donor toDonor(DonorRegisterRequest donorRegisterRequest) {
        Donor donor = new Donor();
        donor.setCnp(donorRegisterRequest.getCnp());
        donor.setFirstName(donorRegisterRequest.getFirstName());
        donor.setLastName(donorRegisterRequest.getLastName());
        donor.setEmail(donorRegisterRequest.getEmail());
        donor.setPassword(donorRegisterRequest.getPassword());
        donor.setRole(Role.DONOR);
        donor.setArea(donorRegisterRequest.getArea());
        donor.setBloodType(donorRegisterRequest.getBloodType());
        donor.setPhoneNumber(donorRegisterRequest.getPhoneNumber());
        return donor;
    }
    public Donor toDonor(UpdateDonorRequest updateDonorRequest) {
        Donor donor = new Donor();
        donor.setId(updateDonorRequest.getId());
        donor.setCnp(updateDonorRequest.getCnp());
        donor.setFirstName(updateDonorRequest.getFirstName());
        donor.setLastName(updateDonorRequest.getLastName());
        donor.setEmail(updateDonorRequest.getEmail());
        donor.setPassword(updateDonorRequest.getPassword());
        donor.setRole(Role.DONOR);
        donor.setArea(updateDonorRequest.getArea());
        donor.setBloodType(updateDonorRequest.getBloodType());
        donor.setPhoneNumber(updateDonorRequest.getPhoneNumber());
        return donor;
    }


}
