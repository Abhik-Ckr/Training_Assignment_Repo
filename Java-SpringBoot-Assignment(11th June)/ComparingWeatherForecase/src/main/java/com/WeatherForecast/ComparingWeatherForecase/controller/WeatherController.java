package com.WeatherForecast.ComparingWeatherForecase.controller;

import com.WeatherForecast.ComparingWeatherForecase.dto.WeatherForecast;
import com.WeatherForecast.ComparingWeatherForecase.model.CityCoordinates;
import com.WeatherForecast.ComparingWeatherForecase.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/forecast")
    public ResponseEntity<?> getForecast(
            @RequestParam(required = true) String city,
            @RequestParam(required = true) double lat,
            @RequestParam(required = true) double lon) {
        try {
            if (city == null || city.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("City name cannot be empty");
            }
            validateCoordinates(lat, lon);
            List<WeatherForecast> forecast = service.getForecast(new CityCoordinates(city, lat, lon));
            return ResponseEntity.ok(forecast);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (RestClientException e) {
            return ResponseEntity.internalServerError().body("Error fetching weather data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/compare")
    public ResponseEntity<?> compareForecasts(
            @RequestParam(required = true) String city1, 
            @RequestParam(required = true) double lat1, 
            @RequestParam(required = true) double lon1,
            @RequestParam(required = true) String city2, 
            @RequestParam(required = true) double lat2, 
            @RequestParam(required = true) double lon2) {
        try {
            if (city1 == null || city1.trim().isEmpty() || city2 == null || city2.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("City names cannot be empty");
            }
            validateCoordinates(lat1, lon1);
            validateCoordinates(lat2, lon2);
            
            Map<String, List<WeatherForecast>> comparison = service.compareForecasts(
                    new CityCoordinates(city1, lat1, lon1),
                    new CityCoordinates(city2, lat2, lon2));
            return ResponseEntity.ok(comparison);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (RestClientException e) {
            return ResponseEntity.internalServerError().body("Error comparing forecasts: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    private void validateCoordinates(double lat, double lon) {
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees");
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees");
        }
    }
}
