package com.example.weatherhttpsenkiv.data.entity.forecastResponse

data class WeatherForecastResponse (
    val dt: Long,
    val list: List<WeatherList>
)