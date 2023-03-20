package com.dice.weather.customexeptions;

public class LocationValidationException extends RuntimeException {
    public LocationValidationException(String error) {
        super(error);
    }
}
