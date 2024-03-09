package com.n11.weatherApi.DAO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMultiDaysResponse {
    int error_code=0;
    String errorMessge="";
    private List<WeatherResponse> responseList;
}
