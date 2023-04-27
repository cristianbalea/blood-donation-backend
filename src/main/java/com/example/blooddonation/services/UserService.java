package com.example.blooddonation.services;

import com.example.blooddonation.controllers.requests.LoginRequest;
import com.example.blooddonation.controllers.responses.DeleteResponse;
import com.example.blooddonation.controllers.responses.UserResponse;
import com.example.blooddonation.domain.dtos.User;
import com.example.blooddonation.domain.repositories.UserRepository;
import com.example.blooddonation.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse loginUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if(userOptional.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        return new UserResponse(userOptional.get());
    }

    public UserResponse readUser(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        return new UserResponse(userOptional.get());
    }

    public DeleteResponse deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        userRepository.delete(user.get());
        return new DeleteResponse(id);
    }
}
