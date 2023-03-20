package com.dice.weather.service;


import com.dice.weather.extract.ExtractWeatherData;
import com.dice.weather.model.Weather;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final ExtractWeatherData extractWeatherData;

    @Value("${rapid.api.key}")
    private String rapidApiKey;

    @Value("${rapid.host}")
    private String rapidHost;

    @Autowired
    public WeatherService(ExtractWeatherData extractWeatherData) {
        this.extractWeatherData = extractWeatherData;
    }

    public List<Weather> fetchForecastByLocationName(String location, String type) throws UnirestException {

        String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + location + "/" + type + "/";

        HttpResponse<String> response = Unirest.get(url)
                .header("X-RapidAPI-Key", rapidApiKey)
                .header("X-RapidAPI-Host", rapidHost)
                .header("Accept", "application/json")
                .asString();
        if (response.getStatus() == HttpStatus.SC_TOO_MANY_REQUESTS)
            throw new UnirestException(response.getBody());

        if(response.getStatus()==HttpStatus.SC_NOT_FOUND) {
            System.out.println(response.getBody());
            throw new UnirestException(response.getBody());

        }
        JSONObject json = new JSONObject(response.getBody());

        return extractWeatherData.extractReverentWeatherInfo(json, type);
//        return new ArrayList<>();
    }


}
