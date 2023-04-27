package com.example.blooddonation.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Setter
@Getter
public class AddAppointmentRequest {
    private UUID locationId;
    private UUID donorId;
    private Date date;
}
