package com.n11.weatherApi.Exception;

public class WeatherServiceException extends RuntimeException {

    private final int code;

    public WeatherServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
