package com.example.blooddonation.config;

import com.example.blooddonation.domain.dtos.*;
import com.example.blooddonation.domain.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Autowired
    public DbInitializer(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    //@PostConstruct
    public void initializer() {
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("admin");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        Location location1 = new Location("Centrul Transfuzie Regina Maria", "Zorilor 34", "Cluj-Napoca", 8, 16, true, 50);
        locationRepository.save(location1);

        Location location2 = new Location("Centru Transfuzie Goga", "Dorobantilor 3", "Cluj-Napoca", 8, 14, true, 50);
        locationRepository.save(location2);

        Location location3 = new Location("Centrul Simtex", "Republicii 25", "Cluj-Napoca", 8, 12, true, 0);
        locationRepository.save(location3);

        Doctor doctor1 = new Doctor("1740415409016", "alexandruion@donate.ro", "mypass", "Alexandru", "Ion", location1);
        userRepository.save(doctor1);

        Doctor doctor2 = new Doctor("2740415015749", "doinitaoancea@donate.ro", "mypass", "Oancea", "Doinita", location1);
        userRepository.save(doctor2);

        Donor donor1 = new Donor("5000730307008", "Joe", "Black", "joeblack@donate.ro", "mypass", "Cluj-Napoca", "AB4");
        userRepository.save(donor1);

        Donor donor2 = new Donor("2900717167901", "Grace", "Stewart", "grace@donate.ro", "mypass", "Turda", "A2");
        userRepository.save(donor2);
    }
}
