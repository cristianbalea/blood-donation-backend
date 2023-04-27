package com.example.blooddonation.services.mappers;

import com.example.blooddonation.controllers.requests.CreateDoctorRequest;
import com.example.blooddonation.controllers.requests.UpdateDoctorRequest;
import com.example.blooddonation.domain.dtos.Doctor;
import com.example.blooddonation.domain.dtos.Role;
import com.example.blooddonation.domain.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    private final LocationRepository locationRepository;

    @Autowired
    public DoctorMapper(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Doctor toDoctor(CreateDoctorRequest createDoctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setCnp(createDoctorRequest.getCnp());
        doctor.setFirstName(createDoctorRequest.getFirstName());
        doctor.setLastName(createDoctorRequest.getLastName());
        doctor.setEmail(createDoctorRequest.getEmail());
        doctor.setPassword(createDoctorRequest.getPassword());
        doctor.setRole(Role.DOCTOR);
        doctor.setLocation(locationRepository.getReferenceById(createDoctorRequest.getLocationId()));
        return doctor;
    }

    public Doctor toDoctor(UpdateDoctorRequest updateDoctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setId(updateDoctorRequest.getId());
        doctor.setCnp(updateDoctorRequest.getCnp());
        doctor.setFirstName(updateDoctorRequest.getFirstName());
        doctor.setLastName(updateDoctorRequest.getLastName());
        doctor.setEmail(updateDoctorRequest.getEmail());
        doctor.setPassword(updateDoctorRequest.getPassword());
        doctor.setRole(Role.DOCTOR);
        doctor.setLocation(locationRepository.getReferenceById(updateDoctorRequest.getLocationId()));
        return doctor;
    }
}
