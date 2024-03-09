package com.n11.weatherApi.Service;

import com.n11.weatherApi.DAO.Response.WeatherResponse;
import com.n11.weatherApi.Exception.WeatherServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherService = new WeatherService(restTemplate);
    }

    @Test
    public void testGetDailyWeatherData_Success() {
        String city = "New York";
        String responseBody = "{\"coord\":" +
                "{\"lon\":12.34,\"lat\":12.34}," +
                "\"weather\":[" +
                "{\"id\":1," +
                "\"main\":\"Clear\"," +
                "\"description\":\"clear sky\"," +
                "\"icon\":\"01d\"}" +
                "]," +
                "\"main\":" +
                "{\"temp\":278.00," +
                "\"feels_like\":273.00," +
                "\"temp_min\":277.00," +
                "\"temp_max\":280.00," +
                "\"pressure\":1020," +
                "\"humidity\":23}," +
                "\"visibility\":10000," +
                "\"wind\":" +
                "{\"speed\":2.57,\"deg\":320}," +
                "\"clouds\":{\"all\":1}," +
                "\"dt\":1667748000," +
                "\"sys\":" +
                "{\"type\":1," +
                "\"id\":4610," +
                "\"country\":\"US\"," +
                "\"sunrise\":1667722390," +
                "\"sunset\":1667766915}," +
                "\"timezone\":-18000," +
                "\"id\":5128581," +
                "\"name\":" +
                "\"New York\"," +
                "\"cod\":200}";

        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<>(responseBody, HttpStatus.OK));

        WeatherResponse weatherResponse = weatherService.getDailyWeatherData(city);

        assertNotNull(weatherResponse);
        assertEquals(277.00, weatherResponse.getMinTemp());
        assertEquals(23, weatherResponse.getHumidity());
        assertEquals("Clear", weatherResponse.getWeatherMain());
    }

    @Test
    public void testGetWeeklyWeather_Unauthorized() {
        String city = "New York";

        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThrows(WeatherServiceException.class, () -> weatherService.getWeeklyWeather(city));
    }

    @Test
    public void testGetMonthlyWeather_Exception() {
        String city = "New York";

        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThrows(WeatherServiceException.class, () -> weatherService.getMonthlyWeather(city));
    }
}
