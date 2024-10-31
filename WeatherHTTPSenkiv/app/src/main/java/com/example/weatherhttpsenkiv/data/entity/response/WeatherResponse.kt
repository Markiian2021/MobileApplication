package com.example.weatherhttpsenkiv.data.entity.response

data class WeatherResponse(
    val coord: WeatherCoordinates,
    val main: WeatherMain,
    val name: String,
    val sys: WeatherSys,
)