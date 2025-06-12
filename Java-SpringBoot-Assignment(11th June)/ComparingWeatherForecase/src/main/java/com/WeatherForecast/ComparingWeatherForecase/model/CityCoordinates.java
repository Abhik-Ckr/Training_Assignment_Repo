package com.WeatherForecast.ComparingWeatherForecase.model;

public class CityCoordinates {
    private String cityName;
    private double latitude;
    private double longitude;

    public CityCoordinates() {}

    public CityCoordinates(String cityName, double latitude, double longitude) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
