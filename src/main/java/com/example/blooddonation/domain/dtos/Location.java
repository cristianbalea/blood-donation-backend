package com.example.blooddonation.domain.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location extends BaseEntity {
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String area;
    @Column
    private int openHour;
    @Column
    private int closingHour;
    @Column
    private Boolean opened;
    @Column
    private int maxNumberOfDonorsPerDay;
}
