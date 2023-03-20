package com.dice.weather.exceptions;

import com.dice.weather.customexeptions.ForecastTypeValidationException;
import com.dice.weather.customexeptions.LocationValidationException;
import com.dice.weather.errors.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = LocationValidationException.class)
    public ResponseEntity<Error> locationValidation(LocationValidationException exception) {
        Error error = new Error(400, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ForecastTypeValidationException.class)
    public ResponseEntity<Error> forecastTypeValidation(ForecastTypeValidationException exception) {
        Error error = new Error(400, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
