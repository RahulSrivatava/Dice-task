package com.dice.weather.model;

import lombok.Data;

@Data
public class Weather {
    private String cityName;
    private String timeZone;
    private Double longitude;
    private Double latitude;
    private String date;
    private Double minTemperature;
    private Double maxTemperature;
    private Double avgTemperature;
    private String windSpeedUnit;
    private String windSpeedDirection;
    private Double minWindSpeed;
    private Double maxWindSpeed;
    private Double avgWindSpeed;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;

    public Weather() {
    }

    public Weather(String cityName, String timeZone, Double longitude, Double latitude, String date, Double minTemperature, Double maxTemperature, Double avgTemperature, String windSpeedUnit, String windSpeedDirection, Double minWindSpeed, Double maxWindSpeed, Double avgWindSpeed, String sunrise, String sunset, String moonrise, String moonset) {
        this.cityName = cityName;
        this.timeZone = timeZone;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.avgTemperature = avgTemperature;
        this.windSpeedUnit = windSpeedUnit;
        this.windSpeedDirection = windSpeedDirection;
        this.minWindSpeed = minWindSpeed;
        this.maxWindSpeed = maxWindSpeed;
        this.avgWindSpeed = avgWindSpeed;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
    }
}
