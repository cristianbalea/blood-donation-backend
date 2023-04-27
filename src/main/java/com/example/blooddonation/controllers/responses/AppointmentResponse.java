package com.example.blooddonation.controllers.responses;

import com.example.blooddonation.domain.dtos.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class AppointmentResponse extends BaseResponse {
    private UUID id;
    private String locationName;
    private String locationAddress;
    private String firstName;
    private String lastName;
    private Date date;
    private Boolean confirmed;

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.locationName = appointment.getLocation().getName();
        this.locationAddress = appointment.getLocation().getAddress();
        this.firstName = appointment.getDonor().getFirstName();
        this.lastName = appointment.getDonor().getLastName();
        this.date = appointment.getDate();
        this.confirmed = appointment.getConfirmed();
    }
}
