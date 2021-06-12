package pl.lukakani.weather.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Bean
    public RestTemplate openWeatherRestTemplate(@Value("${myService.openWeatherUrl}") String baseUrl) {
        return new RestTemplateBuilder()
                .rootUri(baseUrl)
                .build();
    }
}
