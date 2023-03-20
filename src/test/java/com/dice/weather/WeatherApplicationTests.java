package com.dice.weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	private static final String FORECAST_BASE_URL = "http://localhost:3000//api/v1/weather/getForecast";
	@Test
	void getForecast() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		//Get Hourly Weather Description of the Location

		requestParams.add("location", "Berlin");
		requestParams.add("type", "hourly");
		mockMvc.perform(get(FORECAST_BASE_URL).params(requestParams)).andExpect(status().isOk());

		//Get Weather Summary  of the Location
		requestParams.add("location", "Berlin");
		requestParams.add("type", "summary");
		mockMvc.perform(get(FORECAST_BASE_URL).params(requestParams)).andExpect(status().isOk());


	}

}
