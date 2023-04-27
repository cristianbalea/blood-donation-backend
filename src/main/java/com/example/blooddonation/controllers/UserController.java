package com.example.blooddonation.controllers;

import com.example.blooddonation.controllers.requests.LoginRequest;
import com.example.blooddonation.controllers.responses.BaseResponse;
import com.example.blooddonation.controllers.responses.ErrorResponse;
import com.example.blooddonation.exceptions.NotFoundException;
import com.example.blooddonation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(userService.loginUser(loginRequest), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> readUser(@RequestParam("userId") UUID id) {
        try {
            return new ResponseEntity<>(userService.readUser(id), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> deleteUser(@RequestParam("userId") UUID id) {
        try {
            return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
