package com.example.weatherhttpsenkiv.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherhttpsenkiv.data.ServerModule
import com.example.weatherhttpsenkiv.data.entity.forecastResponse.WeatherForecastResponse
import com.example.weatherhttpsenkiv.data.entity.response.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherScreenViewModel(
    private val serverModule: ServerModule
) : ViewModel() {
    private val _weatherResponseStateFlow = MutableStateFlow<WeatherResponse?>(null)
    val weatherResponseStateFlow: StateFlow<WeatherResponse?>
        get() = _weatherResponseStateFlow

    private val _weatherForecastResponseStateFlow = MutableStateFlow<WeatherForecastResponse?>(null)
    val weatherForecastResponseStateFlow: StateFlow<WeatherForecastResponse?>
        get() = _weatherForecastResponseStateFlow

    private val latNumber = 49.8364403
    private val lonNumber = 24.0145909
    init {
        viewModelScope.launch {
            val weatherResponse = serverModule.getCurrentWeather(
                lat = latNumber,
                lon = lonNumber
            )
            _weatherResponseStateFlow.value = weatherResponse

            val weatherForecastResponse = serverModule.getWeatherForecast(
                lat = latNumber,
                lon = lonNumber,
            )
            _weatherForecastResponseStateFlow.value = weatherForecastResponse
        }
    }
}
