package com.example.blooddonation.services;

import com.example.blooddonation.controllers.requests.CreateDoctorRequest;
import com.example.blooddonation.controllers.requests.UpdateDoctorRequest;
import com.example.blooddonation.controllers.responses.DoctorResponse;
import com.example.blooddonation.controllers.responses.ListDoctorResponse;
import com.example.blooddonation.controllers.responses.UserResponse;
import com.example.blooddonation.domain.dtos.Doctor;
import com.example.blooddonation.domain.dtos.Location;
import com.example.blooddonation.domain.dtos.User;
import com.example.blooddonation.domain.repositories.DoctorRepository;
import com.example.blooddonation.domain.repositories.LocationRepository;
import com.example.blooddonation.domain.repositories.UserRepository;
import com.example.blooddonation.exceptions.DuplicateException;
import com.example.blooddonation.exceptions.NotFoundException;
import com.example.blooddonation.services.mappers.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorService(UserRepository userRepository, LocationRepository locationRepository, DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public UserResponse createDoctor(CreateDoctorRequest createDoctorRequest) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(createDoctorRequest.getEmail(), createDoctorRequest.getPassword());

        if(userOptional.isPresent()) {
            throw new DuplicateException("Doctor already exists!");
        }

        Optional<Location> location = locationRepository.findById(createDoctorRequest.getLocationId());
        if(location.isEmpty()) {
            throw new NotFoundException("Location not found!");
        }

        Doctor doctor = doctorMapper.toDoctor(createDoctorRequest);
        userRepository.save(doctor);

        return new UserResponse(doctor);
    }

    public DoctorResponse readDoctor(UUID id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isEmpty()) {
            throw new NotFoundException("Doctor not found!");
        }
        Doctor doctor = doctorOptional.get();
        return new DoctorResponse(doctor);
    }

    public UserResponse updateDoctor(UpdateDoctorRequest updateDoctorRequest) {
        Optional<User> user = userRepository.findById(updateDoctorRequest.getId());

        if(user.isEmpty()) {
            throw new NotFoundException("Doctor not found!");
        }

        User doctor = doctorMapper.toDoctor(updateDoctorRequest);
        userRepository.save(doctor);

        return new UserResponse(doctor);
    }

    public ListDoctorResponse readAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorResponse> doctorResponses = new ArrayList<>();

        doctors.forEach(d -> doctorResponses.add(new DoctorResponse(d)));

        return new ListDoctorResponse(doctorResponses);
    }
}
