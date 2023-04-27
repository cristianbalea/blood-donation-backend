package com.example.blooddonation.domain.repositories;

import com.example.blooddonation.domain.dtos.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    @Override
    Optional<Doctor> findById(UUID id);

    @Override
    List<Doctor> findAll();
}
