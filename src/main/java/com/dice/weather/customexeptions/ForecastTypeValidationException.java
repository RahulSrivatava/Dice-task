package com.dice.weather.customexeptions;

public class ForecastTypeValidationException extends RuntimeException {
    public ForecastTypeValidationException(String error) {
        super(error);
    }
}
