package com.example.blooddonation.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse extends BaseResponse {
    private String message;

    public ErrorResponse() {
        this.message = "";
    }
}
