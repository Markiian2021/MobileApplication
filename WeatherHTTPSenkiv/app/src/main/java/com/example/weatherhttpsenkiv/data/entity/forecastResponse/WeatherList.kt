package com.example.weatherhttpsenkiv.data.entity.forecastResponse

data class WeatherList (
    val id: String,
    val dt: Long,
    val main: WeatherMain,
    val weather: List<WeatherWeather>,
)