package com.example.blooddonation.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class AvailableSpotRequest {
    private Date date;
    private UUID locationId;
}
