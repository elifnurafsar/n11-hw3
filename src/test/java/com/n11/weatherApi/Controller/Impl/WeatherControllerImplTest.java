package com.n11.weatherApi.Controller.Impl;
import com.n11.weatherApi.DAO.Request.WeatherRequest;
import com.n11.weatherApi.DAO.Response.WeatherResponse;
import com.n11.weatherApi.Exception.WeatherServiceException;
import com.n11.weatherApi.Service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherControllerImplTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherControllerImpl weatherController;

    public WeatherControllerImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDailyWeather() throws WeatherServiceException {
        WeatherRequest request = new WeatherRequest("USA", "New York");
        WeatherResponse expectedResponse = createSampleWeatherResponse();
        when(weatherService.getDailyWeatherData("New York")).thenReturn(expectedResponse);

        ResponseEntity<WeatherResponse> responseEntity = weatherController.getDailyWeather(request);

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        verify(weatherService, times(1)).getDailyWeatherData("New York");
    }


    private WeatherResponse createSampleWeatherResponse() {
        WeatherResponse response = new WeatherResponse();
        response.setDayTemp(25.5);
        response.setDayTempFL(23.5);
        response.setMinTemp(20.0);
        response.setMaxTemp(30.0);
        response.setNightTemp(22.0);
        response.setNightTempFL(20.0);
        response.setHumidity(70);
        response.setWeatherMain("Sunny");
        response.setWeatherDescription("Clear skies");
        return response;
    }
}
