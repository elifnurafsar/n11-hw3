package com.n11.weatherApi.Controller.Impl;

import com.n11.weatherApi.Controller.WeatherController;
import com.n11.weatherApi.DAO.Request.WeatherRequest;
import com.n11.weatherApi.DAO.Response.WeatherMultiDaysResponse;
import com.n11.weatherApi.DAO.Response.WeatherResponse;
import com.n11.weatherApi.Exception.WeatherServiceException;
import com.n11.weatherApi.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherControllerImpl implements WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/daily")
    public ResponseEntity<WeatherResponse> getDailyWeather(@RequestBody WeatherRequest request) throws WeatherServiceException{
        WeatherResponse response = weatherService.getDailyWeatherData(request.getCity());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/weekly")
    public ResponseEntity<WeatherMultiDaysResponse> getWeeklyWeather(@RequestBody WeatherRequest request) {
        try {
            WeatherMultiDaysResponse response = weatherService.getWeeklyWeather(request.getCity());
            return ResponseEntity.ok(response);
        } catch (WeatherServiceException e) {
            return handleWeatherServiceException(e);
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<WeatherMultiDaysResponse> getMonthlyWeather(@RequestBody WeatherRequest request) throws WeatherServiceException{
        try {
            WeatherMultiDaysResponse response = weatherService.getMonthlyWeather(request.getCity());
            return ResponseEntity.ok(response);
        } catch (WeatherServiceException e) {
            return handleWeatherServiceException(e);
        }
    }

    @ExceptionHandler(WeatherServiceException.class)
    public ResponseEntity<WeatherMultiDaysResponse> handleWeatherServiceException(WeatherServiceException e) {
        WeatherMultiDaysResponse errorResponse = new WeatherMultiDaysResponse();
        errorResponse.setError_code(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setErrorMessge("Unauthorized: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

}