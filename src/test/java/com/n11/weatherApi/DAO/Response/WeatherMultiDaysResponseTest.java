package com.n11.weatherApi.DAO.Response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherMultiDaysResponseTest {

    @Test
    public void testConstructorWithParameters() {
        List<WeatherResponse> responseList = new ArrayList<>();
        WeatherResponse weatherResponse1 = new WeatherResponse();
        WeatherResponse weatherResponse2 = new WeatherResponse();
        responseList.add(weatherResponse1);
        responseList.add(weatherResponse2);

        WeatherMultiDaysResponse weatherMultiDaysResponse = new WeatherMultiDaysResponse(200, "success", responseList);

        assertNotNull(weatherMultiDaysResponse);
        assertEquals(200, weatherMultiDaysResponse.getError_code());
        assertEquals("success", weatherMultiDaysResponse.getErrorMessge());
        assertEquals(responseList, weatherMultiDaysResponse.getResponseList());
    }

    @Test
    public void testConstructorWithDefaultValues() {
        WeatherMultiDaysResponse weatherMultiDaysResponse = new WeatherMultiDaysResponse();
        List<WeatherResponse> weatherResponseList = new ArrayList<>();
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponseList.add(weatherResponse);
        weatherMultiDaysResponse.setResponseList(weatherResponseList);
        assertNotNull(weatherMultiDaysResponse);
        assertEquals(0, weatherMultiDaysResponse.getError_code());
        assertEquals("", weatherMultiDaysResponse.getErrorMessge());
        assertNotNull(weatherMultiDaysResponse.getResponseList());
        assertEquals(1, weatherMultiDaysResponse.getResponseList().size());
    }

    @Test
    public void testSettersAndGetters() {
        List<WeatherResponse> responseList = new ArrayList<>();
        WeatherResponse weatherResponse = new WeatherResponse();
        responseList.add(weatherResponse);

        WeatherMultiDaysResponse weatherMultiDaysResponse = new WeatherMultiDaysResponse();

        weatherMultiDaysResponse.setError_code(1);
        weatherMultiDaysResponse.setErrorMessge("error");
        weatherMultiDaysResponse.setResponseList(responseList);

        assertEquals(1, weatherMultiDaysResponse.getError_code());
        assertEquals("error", weatherMultiDaysResponse.getErrorMessge());
        assertEquals(responseList, weatherMultiDaysResponse.getResponseList());
    }
}
