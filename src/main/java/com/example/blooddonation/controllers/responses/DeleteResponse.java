package com.example.blooddonation.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class DeleteResponse extends  BaseResponse {
    private UUID id;
}
