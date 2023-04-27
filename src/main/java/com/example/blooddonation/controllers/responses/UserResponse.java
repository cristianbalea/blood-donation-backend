package com.example.blooddonation.controllers.responses;

import com.example.blooddonation.domain.dtos.Role;
import com.example.blooddonation.domain.dtos.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserResponse extends BaseResponse {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }
}
