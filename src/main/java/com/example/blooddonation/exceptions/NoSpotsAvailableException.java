package com.example.blooddonation.exceptions;

public class NoSpotsAvailableException extends RuntimeException{
    public NoSpotsAvailableException(String message) {
        super(message);
    }
}
