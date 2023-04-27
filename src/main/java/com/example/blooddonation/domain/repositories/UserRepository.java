package com.example.blooddonation.domain.repositories;

import com.example.blooddonation.domain.dtos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Override
    Optional<User> findById(UUID uuid);
    Optional<User> findByEmailAndPassword(String email, String password);
}

