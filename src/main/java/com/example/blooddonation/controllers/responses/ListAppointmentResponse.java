package com.example.blooddonation.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListAppointmentResponse extends BaseResponse {
    List<AppointmentResponse> appointmentResponses;
}
