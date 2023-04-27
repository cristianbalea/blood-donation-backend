package com.example.blooddonation.services;

import com.example.blooddonation.controllers.responses.ListLocationResponse;
import com.example.blooddonation.controllers.responses.LocationResponse;
import com.example.blooddonation.domain.dtos.Location;
import com.example.blooddonation.domain.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public ListLocationResponse getAllLocations() {
        List<LocationResponse> locations = new ArrayList<>();

        List<Location> locationList = locationRepository.findAll();

        for(Location l : locationList) {
            locations.add(new LocationResponse(l));
        }

        return new ListLocationResponse(locations);
    }
}
