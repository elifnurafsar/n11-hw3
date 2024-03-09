package com.n11.weatherApi.Service;

import com.n11.weatherApi.Converter.Converter;
import com.n11.weatherApi.DAO.Response.WeatherResponse;
import com.n11.weatherApi.DAO.Response.WeatherMultiDaysResponse;
import com.n11.weatherApi.Exception.WeatherServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${api.openweathermap.key}")
    private String apiKey;

    private final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5";
    private final String WEATHER_API_30_DAYS_URL = "https://pro.openweathermap.org/data/2.5/forecast";
    private final String WEATHER_API_WEEKLY_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getDailyWeatherData(String city) {
        String url = WEATHER_API_URL + "/weather?q=" + city + "&appid=" + apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return Converter.convertToWeatherResponse(response.getBody());
    }

    public WeatherMultiDaysResponse getWeeklyWeather(String city) throws WeatherServiceException {
        String url = WEATHER_API_WEEKLY_URL + "/daily?q=" + city + "&cnt=" + 7 + "&appid=" + apiKey;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return Converter.convertToWeatherMultiDaysResponse(response);
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new WeatherServiceException(e.getStatusCode().value(), e.getMessage());
        } catch (Exception e) {
            throw new WeatherServiceException(1, "Error fetching weather data: " + e.getMessage());
        }
    }

    public WeatherMultiDaysResponse getMonthlyWeather(String city) {
        String url = WEATHER_API_30_DAYS_URL + "/climate?q=" + city + "&appid=" + apiKey;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return Converter.convertToWeatherMultiDaysResponse(response);
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new WeatherServiceException(e.getStatusCode().value(), e.getMessage());
        } catch (Exception e) {
            throw new WeatherServiceException(1, "Error fetching weather data: " + e.getMessage());
        }
    }
}
