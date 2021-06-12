package pl.lukakani.weather.model;

import lombok.*;

@Data
public class WeatherDto {
    private String description;
    private String main;
    private WeatherDetails weatherDetails;
}
