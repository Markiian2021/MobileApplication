package com.example.weatherhttpsenkiv.data

import com.example.weatherhttpsenkiv.data.entity.forecastResponse.WeatherForecastResponse
import com.example.weatherhttpsenkiv.data.entity.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ServerModule {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = "38f6132e839671f02639a6957a5f9f76",
    ): WeatherResponse

    @GET("/data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiId: String = "38f6132e839671f02639a6957a5f9f76",
    ): WeatherForecastResponse
}