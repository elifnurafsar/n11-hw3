package com.n11.weatherApi.DAO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private double dayTemp;
    private double dayTempFL;
    private double minTemp;
    private double maxTemp;
    private double nightTemp;
    private double nightTempFL;
    private int humidity;
    private String weatherMain;
    private String weatherDescription;
}