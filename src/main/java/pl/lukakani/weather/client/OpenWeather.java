package pl.lukakani.weather.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.lukakani.weather.model.WeatherBasic;
import pl.lukakani.weather.model.WeatherDetails;

import java.util.List;

@Data
public class OpenWeather {

    @JsonProperty("main")
    private WeatherDetails weatherDetails;
    private List<WeatherBasic> weather;

}
