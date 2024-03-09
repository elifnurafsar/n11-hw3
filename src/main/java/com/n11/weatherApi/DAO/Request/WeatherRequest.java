package com.n11.weatherApi.DAO.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {
    // Validations
    @NotBlank(message = "Country must not be blank")
    private String country;

    @NotBlank(message = "City must not be blank")
    private String city;
}
