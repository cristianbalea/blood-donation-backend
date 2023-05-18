package com.example.blooddonation.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class SetReminderRequest {
    public UUID id;
    public Boolean remind;
}
