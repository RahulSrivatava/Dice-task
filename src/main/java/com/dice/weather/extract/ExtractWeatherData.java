package com.dice.weather.extract;

import com.dice.weather.model.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("extract")
public class ExtractWeatherData {

    public List<Weather> extractReverentWeatherInfo(JSONObject json, String type) {

        List<Weather> weatherForecastSummary = new ArrayList<>();

        /* assign variable */
        JSONArray jsonArray = json.getJSONObject("forecast").getJSONArray("items");

        JSONObject jsonObject = json.getJSONObject("location");

        String cityName = jsonObject.getString("name");
        String timeZone = jsonObject.getString("timezone");
        Double longitude = jsonObject.getJSONObject("coordinates").getDouble("longitude");
        Double latitude = jsonObject.getJSONObject("coordinates").getDouble("latitude");


        Double avgTemperature = null, avgWindSpeed = null;
        Double maxTemperature = null, minTemperature = null;
        Double maxWindSpeed = null, minWindSpeed = null;
        String sunrise = "", sunset = "", moonrise = "", moonset = "";


        for (int i = 0; i < jsonArray.length(); ++i) {

            String date = jsonArray.getJSONObject(i).getString("date");
            System.out.println(date);


            String windSpeedUnit = jsonArray.getJSONObject(i).getJSONObject("wind").getString("unit");
            System.out.println(windSpeedUnit);

            String windSpeedDirection = jsonArray.getJSONObject(i).getJSONObject("wind").getString("direction");
            System.out.println(windSpeedDirection);


            if (type.equals("hourly")) {
                avgTemperature = jsonArray.getJSONObject(i).getJSONObject("temperature").getDouble("avg");
                avgWindSpeed = jsonArray.getJSONObject(i).getJSONObject("wind").getDouble("avg");
            }


            if (type.equals("summary")) {

                maxTemperature = jsonArray.getJSONObject(i).getJSONObject("temperature").getDouble("max");
                System.out.println(maxTemperature);

                minTemperature = jsonArray.getJSONObject(i).getJSONObject("temperature").getDouble("min");
                System.out.println(minTemperature);

                maxWindSpeed = jsonArray.getJSONObject(i).getJSONObject("wind").getDouble("max");
                System.out.println(maxWindSpeed);

                minWindSpeed = jsonArray.getJSONObject(i).getJSONObject("wind").getDouble("min");
                System.out.println(minWindSpeed);

                sunrise = jsonArray.getJSONObject(i).getJSONObject("astronomy").getString("sunrise");
                System.out.println(sunrise);

                sunset = jsonArray.getJSONObject(i).getJSONObject("astronomy").getString("sunset");
                System.out.println(sunset);

                moonrise = jsonArray.getJSONObject(i).getJSONObject("astronomy").getString("moonrise");
                System.out.println(moonrise);

                if (jsonArray.getJSONObject(i).getJSONObject("astronomy").get("moonset") instanceof String)
                    moonset = jsonArray.getJSONObject(i).getJSONObject("astronomy").getString("moonset");
            }


            Weather weather = new Weather(
                    cityName,
                    timeZone,
                    longitude,
                    latitude,
                    date,
                    minTemperature,
                    maxTemperature,
                    avgTemperature,
                    windSpeedUnit,
                    windSpeedDirection,
                    maxWindSpeed,
                    minWindSpeed,
                    avgWindSpeed,
                    sunrise,
                    sunset,
                    moonrise,
                    moonset
            );

            weatherForecastSummary.add(weather);
        }

        return weatherForecastSummary;
    }
}
