package com.n11.weatherApi.Controller;

import com.n11.weatherApi.DAO.Request.WeatherRequest;
import com.n11.weatherApi.DAO.Response.WeatherMultiDaysResponse;
import com.n11.weatherApi.DAO.Response.WeatherResponse;
import com.n11.weatherApi.Exception.WeatherServiceException;
import org.springframework.http.ResponseEntity;

public interface WeatherController {

    ResponseEntity<WeatherResponse> getDailyWeather(WeatherRequest request) throws WeatherServiceException;

    ResponseEntity<WeatherMultiDaysResponse> getWeeklyWeather(WeatherRequest request) throws WeatherServiceException;

    ResponseEntity<WeatherMultiDaysResponse> getMonthlyWeather(WeatherRequest request) throws WeatherServiceException;
}