package com.n11.weatherApi.Exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceExceptionTest {

    @Test
    public void testWeatherServiceException() {
        int code = 401;
        String message = "Request not allowed!";

        WeatherServiceException exception = new WeatherServiceException(code, message);

        assertEquals(code, exception.getCode());
        assertEquals(message, exception.getMessage());
    }
}
