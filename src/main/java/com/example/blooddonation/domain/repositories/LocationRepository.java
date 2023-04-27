package com.example.blooddonation.domain.repositories;

import com.example.blooddonation.domain.dtos.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Override
    List<Location> findAll();
}
