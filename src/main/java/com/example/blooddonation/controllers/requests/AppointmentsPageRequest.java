package com.example.blooddonation.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AppointmentsPageRequest {
    private UUID locationId;
    private int offset;
}
