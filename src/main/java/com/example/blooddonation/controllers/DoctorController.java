package com.example.blooddonation.controllers;

import com.example.blooddonation.controllers.requests.CreateDoctorRequest;
import com.example.blooddonation.controllers.requests.UpdateDoctorRequest;
import com.example.blooddonation.controllers.responses.BaseResponse;
import com.example.blooddonation.controllers.responses.ErrorResponse;
import com.example.blooddonation.exceptions.DuplicateException;
import com.example.blooddonation.exceptions.NotFoundException;
import com.example.blooddonation.services.DoctorService;
import com.example.blooddonation.services.UserService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> createDoctor(@RequestBody CreateDoctorRequest createDoctorRequest) {
        try {
            return new ResponseEntity<>(doctorService.createDoctor(createDoctorRequest), HttpStatus.OK);
        } catch(DuplicateException | NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/doctor")
    public ResponseEntity<BaseResponse> readDoctor(@RequestParam("doctorId") UUID id) {
        try {
            return new ResponseEntity<>(doctorService.readDoctor(id), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse> updateDoctor(@RequestBody UpdateDoctorRequest updateDoctorRequest) {
        try {
            return new ResponseEntity<>(doctorService.updateDoctor(updateDoctorRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> readAllDoctors() {
        return new ResponseEntity<>(doctorService.readAll(), HttpStatus.OK);
    }
}
