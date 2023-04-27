package com.example.blooddonation.domain.repositories;

import com.example.blooddonation.domain.dtos.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DonorRepository extends JpaRepository<Donor, UUID> {
    @Override
    Optional<Donor> findById(UUID uuid);
}
