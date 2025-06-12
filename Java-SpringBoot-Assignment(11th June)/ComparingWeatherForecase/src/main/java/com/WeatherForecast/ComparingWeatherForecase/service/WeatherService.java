package com.WeatherForecast.ComparingWeatherForecase.service;

import com.WeatherForecast.ComparingWeatherForecase.dto.WeatherForecast;
import com.WeatherForecast.ComparingWeatherForecase.model.CityCoordinates;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<WeatherForecast> getForecast(CityCoordinates city) throws JSONException {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + city.getLatitude() +
                    "&longitude=" + city.getLongitude() + "&daily=temperature_2m_max,temperature_2m_min&timezone=auto";

            String response = restTemplate.getForObject(url, String.class);
            if (response == null) {
                throw new RuntimeException("No response from weather API");
            }

            JSONObject root = new JSONObject(response);
            JSONObject daily = root.getJSONObject("daily");

            List<WeatherForecast> forecasts = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                LocalDate date = LocalDate.parse(daily.getJSONArray("time").getString(i));
                double temperature = daily.getJSONArray("temperature_2m_max").getDouble(i);
                forecasts.add(new WeatherForecast(date, temperature));
            }
            return forecasts;
        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching weather data: " + e.getMessage(), e);
        } catch (JSONException e) {
            throw new RuntimeException("Error parsing weather data: " + e.getMessage(), e);
        }
    }

    public Map<String, List<WeatherForecast>> compareForecasts(CityCoordinates city1, CityCoordinates city2) {
        try {
            List<WeatherForecast> forecast1 = getForecast(city1);
            List<WeatherForecast> forecast2 = getForecast(city2);

            Map<String, List<WeatherForecast>> result = new HashMap<>();
            result.put(city1.getCityName(), forecast1);
            result.put(city2.getCityName(), forecast2);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error comparing forecasts: " + e.getMessage(), e);
        }
    }
}