package com.example.blooddonation.controllers;

import com.example.blooddonation.controllers.requests.AddAppointmentRequest;
import com.example.blooddonation.controllers.requests.AppointmentsPageRequest;
import com.example.blooddonation.controllers.requests.AvailableSpotRequest;
import com.example.blooddonation.controllers.responses.BaseResponse;
import com.example.blooddonation.controllers.responses.ErrorResponse;
import com.example.blooddonation.exceptions.NoSpotsAvailableException;
import com.example.blooddonation.exceptions.NotFoundException;
import com.example.blooddonation.services.AppointmentService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> addNewAppointment(@RequestBody AddAppointmentRequest addAppointmentRequest) {
        try {
            return new ResponseEntity<>(appointmentService.addNewAppointment(addAppointmentRequest), HttpStatus.OK);
        } catch (NotFoundException | NoSpotsAvailableException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAppointmentsByDonor(@RequestParam("userId") UUID id) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByDonor(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> deleteAppointment(@RequestParam("appointmentId") UUID id) {
        try {
            return new ResponseEntity<>(appointmentService.deleteAppointment(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/location")
    public ResponseEntity<BaseResponse> getAppointmentsByLocation(@RequestParam("locationId") UUID id) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByLocation(id), HttpStatus.OK);
    }

    @PutMapping("/confirm")
    public ResponseEntity<BaseResponse> confirmAppointment(@RequestParam("appointmentId") UUID id) {
        try {
            return new ResponseEntity<>(appointmentService.confirmAppointment(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/today")
    public ResponseEntity<BaseResponse> getCurrentDayAppointments(@RequestParam("locationId") UUID id) {
        return new ResponseEntity<>(appointmentService.getCurrentDayAppointments(id), HttpStatus.OK);
    }

    @PostMapping("/page")
    public ResponseEntity<BaseResponse> getAppointmentsPage(@RequestBody AppointmentsPageRequest appointmentsPageRequest) {
        return new ResponseEntity<>(
                appointmentService.getAppointmentsLimit(appointmentsPageRequest.getLocationId(),
                        appointmentsPageRequest.getOffset()),
                HttpStatus.OK);
    }
    @PostMapping("/available")
    public ResponseEntity<BaseResponse> checkAvailableSpot(@RequestBody AvailableSpotRequest availableSpotRequest) {
        return new ResponseEntity<>(appointmentService.checkSpotAvailability(availableSpotRequest.getDate(), availableSpotRequest.getLocationId()), HttpStatus.OK);
    }
}
