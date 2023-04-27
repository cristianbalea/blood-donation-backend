package com.example.blooddonation.controllers.responses;

import com.example.blooddonation.domain.dtos.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class LocationResponse extends BaseResponse {
    private UUID id;
    private String name;
    private String address;
    private String area;
    private int openHour;
    private int closingHour;
    private Boolean opened;

    public LocationResponse(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.address = location.getAddress();
        this.area = location.getArea();
        this.openHour = location.getOpenHour();
        this.closingHour = location.getClosingHour();
        this.opened = location.getOpened();
    }
}
