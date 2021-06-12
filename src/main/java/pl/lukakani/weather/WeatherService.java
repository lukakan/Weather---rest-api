package pl.lukakani.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lukakani.weather.client.OpenWeather;
import pl.lukakani.weather.model.WeatherDto;

@Service
public class WeatherService {

    private final RestTemplate openWeatherRestTemplate;
    private final String apiKey;

    @Autowired
    public WeatherService(@Value("${myService.openWeatherApiKey}") String apiKey, RestTemplate openWeatherRestTemplate) {
        this.apiKey = apiKey;
        this.openWeatherRestTemplate = openWeatherRestTemplate;
    }

    public WeatherDto getWeatherByCityName(String cityName) {
        String url = "/weather?q=" + cityName + "&" + apiKey;
        OpenWeather response = openWeatherRestTemplate.getForObject(url, OpenWeather.class);
        if (response != null) {
            return toDto(response);
        }

        return null;
    }

    private WeatherDto toDto(OpenWeather openWeather) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setWeatherDetails(openWeather.getWeatherDetails());
        weatherDto.setDescription(openWeather.getWeather().get(0).getDescription());
        weatherDto.setMain(openWeather.getWeather().get(0).getMain());
        return weatherDto;
    }
}
