package pl.lukakani.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukakani.weather.model.CityDto;
import pl.lukakani.weather.model.WeatherDto;

import java.net.UnknownHostException;

@Controller
public class HomeController {

    private final WeatherService weatherService;

    @Autowired
    public HomeController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("city", new CityDto());
        return "home";
    }

    @PostMapping("/weather")
    public String getWeatherPageForm(CityDto cityDto, RedirectAttributes redirectAttributes) {
        return "redirect:/weather?city=" + cityDto.getName();
    }

    @GetMapping("/weather")
    public String getWeatherPage(@RequestParam String city, Model model) {
        WeatherDto weatherDto = weatherService.getWeatherByCityName(city);
        model.addAttribute("city", new CityDto(city));
        model.addAttribute("weather", weatherDto);
        return "weather";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleWrongUrlException(Model model) {
        model.addAttribute("message", "Not correct city name provided");
        return "error";
    }

    @ExceptionHandler(UnknownHostException.class)
    public String handleApiConnectionException(Model model) {
        model.addAttribute("message", "Client connection error. Please try again later one");
        return "error";
    }



}
