package com.example.blooddonation.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ListDoctorResponse extends BaseResponse {
    private List<DoctorResponse> doctorResponseList;
}
