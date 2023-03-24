package com.dice.weather.controller;

import com.dice.weather.customexeptions.ForecastTypeValidationException;
import com.dice.weather.customexeptions.LocationValidationException;
import com.dice.weather.errors.Error;
import com.dice.weather.model.AuthRequest;
import com.dice.weather.model.AuthResponse;
import com.dice.weather.model.Weather;
import com.dice.weather.service.JwtService;
import com.dice.weather.service.WeatherService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/status")
    public String checkApiStatus() throws UnirestException {
        System.out.println("Hello World ");
        HttpResponse<String> response = Unirest.get("https://forecast9.p.rapidapi.com/status/")
                .header("X-RapidAPI-Key", "e4b29160cfmsha1fc0c7b57bd76cp114691jsn42d39c1cf99e")
                .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .asString();

        return response.getBody();
    }


    @GetMapping("/getForecast")
    @ResponseBody
    public ResponseEntity<?> getForecastSummaryByLocationName(@RequestParam String location, @RequestParam String type) {

        if (!location.toLowerCase().matches("^[a-zA-Z]*$"))
            throw new LocationValidationException("Location contains non alphabetical character :: " + location);

        if (!type.toLowerCase().equals("summary") && !type.toLowerCase().equals("hourly"))
            throw new ForecastTypeValidationException("Forecast type must be summary or hourly only :: " + type);

        try {
            List<Weather> response = weatherService.fetchForecastByLocationName(location.toLowerCase(), type.toLowerCase());
            return ResponseEntity.ok(response);

        } catch (UnirestException e) {
            Error error = new Error(400, e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/testForecastSummary/{location}")
    public String testForecastSummaryByLocationName(@PathVariable("location") String location) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://forecast9.p.rapidapi.com/rapidapi/forecast/Berlin/summary/")
                .header("X-RapidAPI-Key", "e4b29160cfmsha1fc0c7b57bd76cp114691jsn42d39c1cf99e")
                .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .asString();
        return response.getBody();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Unathorized");
        }
        String token = jwtService.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }


}
