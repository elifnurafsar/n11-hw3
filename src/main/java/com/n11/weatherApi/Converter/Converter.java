package com.n11.weatherApi.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.n11.weatherApi.DAO.Response.ApiResponse;
import com.n11.weatherApi.DAO.Response.WeatherMultiDaysResponse;
import com.n11.weatherApi.DAO.Response.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static WeatherResponse convertToWeatherResponse(String responseString) {
        try {
            JsonNode jsonResponse = objectMapper.readTree(responseString);

            JsonNode mainData = jsonResponse.get("main");
            double dayTemp = mainData.get("temp").asDouble();
            double dayTempFL = mainData.get("feels_like").asDouble();
            double minTemp = mainData.get("temp_min").asDouble();
            double maxTemp = mainData.get("temp_max").asDouble();
            int humidity = mainData.get("humidity").asInt();

            JsonNode weatherData = jsonResponse.get("weather").get(0);
            String weatherMain = weatherData.get("main").asText();
            String weatherDescription = weatherData.get("description").asText();

            WeatherResponse weatherResponse = new WeatherResponse();
            weatherResponse.setDayTemp(dayTemp);
            weatherResponse.setDayTempFL(dayTempFL);
            weatherResponse.setMinTemp(minTemp);
            weatherResponse.setMaxTemp(maxTemp);
            weatherResponse.setHumidity(humidity);
            weatherResponse.setWeatherMain(weatherMain);
            weatherResponse.setWeatherDescription(weatherDescription);

            return weatherResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static WeatherMultiDaysResponse convertToWeatherMultiDaysResponse(ResponseEntity<String> responseEntity) {
        ApiResponse apiResponse = parseApiResponse(responseEntity.getBody());
        List<WeatherResponse> weatherResponses = new ArrayList<>();
        for (ApiResponse.WeeklyWeatherData data : apiResponse.getList()) {
            WeatherResponse weatherResponse = new WeatherResponse();
            weatherResponse.setDayTemp(data.getTemp().getDay());
            weatherResponse.setDayTempFL(data.getFeels_like().getDay());
            weatherResponse.setMinTemp(data.getTemp().getMin());
            weatherResponse.setMaxTemp(data.getTemp().getMax());
            weatherResponse.setNightTemp(data.getTemp().getNight());
            weatherResponse.setNightTempFL(data.getFeels_like().getNight());
            weatherResponse.setHumidity(data.getHumidity());
            weatherResponse.setWeatherMain(data.getWeather().get(0).getMain());
            weatherResponse.setWeatherDescription(data.getWeather().get(0).getDescription());
            weatherResponses.add(weatherResponse);
        }
        return new WeatherMultiDaysResponse(0, "", weatherResponses);
    }

    private static ApiResponse parseApiResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBody, ApiResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
