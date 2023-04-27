package com.example.blooddonation.controllers;

import com.example.blooddonation.controllers.responses.BaseResponse;
import com.example.blooddonation.controllers.responses.ErrorResponse;
import com.example.blooddonation.exceptions.NotFoundException;
import com.example.blooddonation.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllLocations() {
        try {
            return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
