package com.example.blooddonation.services;

import com.example.blooddonation.controllers.requests.DonorRegisterRequest;
import com.example.blooddonation.controllers.requests.UpdateDonorRequest;
import com.example.blooddonation.controllers.responses.DonorResponse;
import com.example.blooddonation.domain.dtos.Donor;
import com.example.blooddonation.domain.dtos.User;
import com.example.blooddonation.domain.repositories.DonorRepository;
import com.example.blooddonation.domain.repositories.UserRepository;
import com.example.blooddonation.exceptions.DuplicateException;
import com.example.blooddonation.exceptions.NotFoundException;
import com.example.blooddonation.services.mappers.DonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DonorService {
    private final UserRepository userRepository;

    private final DonorRepository donorRepository;
    private final DonorMapper donorMapper;

    @Autowired
    public DonorService(UserRepository userRepository, DonorRepository donorRepository, DonorMapper donorMapper) {
        this.userRepository = userRepository;
        this.donorRepository = donorRepository;
        this.donorMapper = donorMapper;
    }

    public DonorResponse readDonor(UUID id) {
        Optional<Donor> donorOptional = donorRepository.findById(id);
        if(donorOptional.isEmpty()) {
            throw new NotFoundException("Donor not found!");
        }
        Donor donor = donorOptional.get();
        return new DonorResponse(donor);
    }

    public DonorResponse registerDonor(DonorRegisterRequest donorRegisterRequest) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(donorRegisterRequest.getEmail(), donorRegisterRequest.getPassword());

        if(userOptional.isPresent()) {
            throw new DuplicateException("Donor already exists!");
        }

        Donor donor = donorMapper.toDonor(donorRegisterRequest);
        userRepository.save(donor);

        return new DonorResponse(donor);
    }

    public DonorResponse updateDoctor(UpdateDonorRequest updateDonorRequest) {
        Optional<User> user = userRepository.findById(updateDonorRequest.getId());

        if(user.isEmpty()) {
            throw new NotFoundException("Donor not found!");
        }

        Donor donor = donorMapper.toDonor(updateDonorRequest);
        userRepository.save(donor);

        return new DonorResponse(donor);
    }
}
