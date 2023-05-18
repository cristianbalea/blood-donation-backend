package com.example.blooddonation.controllers;

import com.example.blooddonation.controllers.requests.DonorRegisterRequest;
import com.example.blooddonation.controllers.requests.SetReminderRequest;
import com.example.blooddonation.controllers.requests.UpdateDoctorRequest;
import com.example.blooddonation.controllers.requests.UpdateDonorRequest;
import com.example.blooddonation.controllers.responses.BaseResponse;
import com.example.blooddonation.controllers.responses.ErrorResponse;
import com.example.blooddonation.exceptions.DuplicateException;
import com.example.blooddonation.exceptions.NotFoundException;
import com.example.blooddonation.services.DonorService;
import org.apache.coyote.Response;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/donor")
public class DonorController {
    private final DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> readDonor(@RequestParam("donorId") UUID id) {
        try {
            return new ResponseEntity<>(donorService.readDonor(id), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerDonor(@RequestBody DonorRegisterRequest donorRegisterRequest) {
        try {
            return new ResponseEntity<>(donorService.registerDonor(donorRegisterRequest), HttpStatus.OK);
        } catch(DuplicateException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse> updateDonor(@RequestBody UpdateDonorRequest updateDonorRequest) {
        try {
            return new ResponseEntity<>(donorService.updateDonor(updateDonorRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reminder/sms")
    public ResponseEntity<BaseResponse> setSmsReminder(@RequestBody SetReminderRequest setReminderRequest) {
        try {
            return new ResponseEntity<>(donorService.setSmsReminder(setReminderRequest), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reminder/mail")
    public ResponseEntity<BaseResponse> setMailReminder(@RequestBody SetReminderRequest setReminderRequest) {
        try {
            return new ResponseEntity<>(donorService.setMailReminder(setReminderRequest), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
