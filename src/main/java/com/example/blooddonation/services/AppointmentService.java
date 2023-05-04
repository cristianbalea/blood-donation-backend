package com.example.blooddonation.services;

import com.example.blooddonation.controllers.requests.AddAppointmentRequest;
import com.example.blooddonation.controllers.responses.AppointmentResponse;
import com.example.blooddonation.controllers.responses.AvailableSpotResponse;
import com.example.blooddonation.controllers.responses.DeleteResponse;
import com.example.blooddonation.controllers.responses.ListAppointmentResponse;
import com.example.blooddonation.domain.dtos.Appointment;
import com.example.blooddonation.domain.dtos.Donor;
import com.example.blooddonation.domain.dtos.Location;
import com.example.blooddonation.domain.repositories.AppointmentRepository;
import com.example.blooddonation.domain.repositories.DonorRepository;
import com.example.blooddonation.domain.repositories.LocationRepository;
import com.example.blooddonation.exceptions.NoSpotsAvailableException;
import com.example.blooddonation.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final LocationRepository locationRepository;
    private final DonorRepository donorRepository;
    private final MailSenderService mailSenderService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, LocationRepository locationRepository, DonorRepository donorRepository, MailSenderService mailSenderService) {
        this.appointmentRepository = appointmentRepository;
        this.locationRepository = locationRepository;
        this.donorRepository = donorRepository;
        this.mailSenderService = mailSenderService;
    }

    public AppointmentResponse addNewAppointment(AddAppointmentRequest addAppointmentRequest) {
        Optional<Location> location = locationRepository.findById(addAppointmentRequest.getLocationId());

        if(location.isEmpty()) {
            throw new NotFoundException("Location not found!");
        }

        Optional<Donor> donor = donorRepository.findById(addAppointmentRequest.getDonorId());

        if(donor.isEmpty()) {
            throw new NotFoundException("Donor not found!");
        }

        int numberOfAppointments = appointmentRepository.checkNumberOfAppointments(addAppointmentRequest.getDate(),
                location.get().getId());

        if(numberOfAppointments >= location.get().getMaxNumberOfDonorsPerDay()) {
            throw new NoSpotsAvailableException("No available spots for donation!");
        }

        Appointment appointment = new Appointment();
        appointment.setConfirmed(false);
        appointment.setDate(addAppointmentRequest.getDate());
        appointment.setDonor(donor.get());
        appointment.setLocation(location.get());

        appointmentRepository.save(appointment);

        mailSenderService.sendConfirmation(appointment);

        return new AppointmentResponse(appointment);
    }

    public ListAppointmentResponse getAppointmentsByDonor(UUID id) {
        List<Appointment> appointments = appointmentRepository.findAllByDonor_Id(id);

        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for(Appointment a : appointments) {
            appointmentResponses.add(new AppointmentResponse(a));
        }

        return new ListAppointmentResponse(appointmentResponses);
    }

    public DeleteResponse deleteAppointment(UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if(appointment.isEmpty()) {
            throw new NotFoundException("Appointment not found!");
        }

        appointmentRepository.deleteById(id);

        return new DeleteResponse(id);
    }

    public ListAppointmentResponse getAppointmentsByLocation(UUID id) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByLocation_Id(id);

        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for(Appointment a : appointments) {
            appointmentResponses.add(new AppointmentResponse(a));
        }

        return new ListAppointmentResponse(appointmentResponses);
    }

    public AppointmentResponse confirmAppointment(UUID id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if(appointment.isEmpty()) {
            throw new NotFoundException("Appointment not found!");
        }

        appointmentRepository.confirmAppointment(id);

        Optional<Appointment> appointmentConfirmed = appointmentRepository.findById(id);

        Appointment result = appointmentConfirmed.get();
        result.setConfirmed(true);

        return new AppointmentResponse(result);
    }

    public ListAppointmentResponse getCurrentDayAppointments(UUID id) {
        Date date = new Date(System.currentTimeMillis());
        Date todaysDate = new Date(date.getYear(), date.getMonth(), date.getDate());
        List<Appointment> appointments = appointmentRepository.getAppointmentsByLocation_IdAndDate(id, todaysDate);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for(Appointment a : appointments) {
            appointmentResponses.add(new AppointmentResponse(a));
        }

        return new ListAppointmentResponse(appointmentResponses);
    }

    public ListAppointmentResponse getAppointmentsLimit(UUID id, int offset) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsLimit(id, offset);
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for(Appointment a : appointments) {
            appointmentResponses.add(new AppointmentResponse(a));
        }

        return new ListAppointmentResponse(appointmentResponses);
    }

    public AvailableSpotResponse checkSpotAvailability(Date date, UUID locationId) {
        if(appointmentRepository.checkAvailableSpot(date, locationId) == null) {
            return new AvailableSpotResponse(true);
        }
        return new AvailableSpotResponse(appointmentRepository.checkAvailableSpot(date, locationId));
    }
}
