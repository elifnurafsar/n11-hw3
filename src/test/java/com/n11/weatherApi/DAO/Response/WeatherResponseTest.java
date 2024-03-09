package com.n11.weatherApi.DAO.Response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherResponseTest {

    @Test
    public void testWeatherResponseConstructor() {
        double dayTemp = 20.5;
        double dayTempFL = 18.5;
        double minTemp = 15.0;
        double maxTemp = 25.0;
        double nightTemp = 18.0;
        double nightTempFL = 16.0;
        int humidity = 70;
        String weatherMain = "Cloudy";
        String weatherDescription = "Partly cloudy";

        WeatherResponse response = new WeatherResponse(dayTemp, dayTempFL, minTemp, maxTemp, nightTemp, nightTempFL, humidity, weatherMain, weatherDescription);

        assertEquals(dayTemp, response.getDayTemp());
        assertEquals(dayTempFL, response.getDayTempFL());
        assertEquals(minTemp, response.getMinTemp());
        assertEquals(maxTemp, response.getMaxTemp());
        assertEquals(nightTemp, response.getNightTemp());
        assertEquals(nightTempFL, response.getNightTempFL());
        assertEquals(humidity, response.getHumidity());
        assertEquals(weatherMain, response.getWeatherMain());
        assertEquals(weatherDescription, response.getWeatherDescription());
    }

    @Test
    public void testWeatherResponseSetter() {
        WeatherResponse response = new WeatherResponse();

        response.setDayTemp(20.5);
        response.setDayTempFL(18.5);
        response.setMinTemp(15.0);
        response.setMaxTemp(25.0);
        response.setNightTemp(18.0);
        response.setNightTempFL(16.0);
        response.setHumidity(70);
        response.setWeatherMain("Cloudy");
        response.setWeatherDescription("Partly cloudy");

        assertEquals(20.5, response.getDayTemp());
        assertEquals(18.5, response.getDayTempFL());
        assertEquals(15.0, response.getMinTemp());
        assertEquals(25.0, response.getMaxTemp());
        assertEquals(18.0, response.getNightTemp());
        assertEquals(16.0, response.getNightTempFL());
        assertEquals(70, response.getHumidity());
        assertEquals("Cloudy", response.getWeatherMain());
        assertEquals("Partly cloudy", response.getWeatherDescription());
    }
}
