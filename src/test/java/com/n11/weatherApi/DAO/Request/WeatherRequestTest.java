package com.n11.weatherApi.DAO.Request;

import org.junit.jupiter.api.Test;
import jakarta.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherRequestTest {

    private Validator validator;

    public WeatherRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidWeatherRequest() {
        WeatherRequest request = new WeatherRequest("USA", "New York");
        Set<ConstraintViolation<WeatherRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    public void testInvalidWeatherRequest() {
        WeatherRequest request = new WeatherRequest("", "");
        Set<ConstraintViolation<WeatherRequest>> violations = validator.validate(request);
        assertEquals(2, violations.size(), "Expected 2 validation errors");
        for (ConstraintViolation<WeatherRequest> violation : violations) {
            assertTrue(violation.getMessage().contains("must not be blank"), "Validation message must contain 'must not be blank'");
        }
    }
}
