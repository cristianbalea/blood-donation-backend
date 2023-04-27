package com.example.blooddonation.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ListLocationResponse extends BaseResponse {
    private List<LocationResponse> locationResponses;
}
