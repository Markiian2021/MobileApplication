package com.example.weatherhttpsenkiv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherhttpsenkiv.data.entity.forecastResponse.WeatherIcon
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherDayScreen(
    dateInMillis: Long,
    viewModel: WeatherScreenViewModel = getViewModel()
) {
    val weatherForecastResponseState = viewModel.weatherForecastResponseStateFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xF2CCCCCC))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        weatherForecastResponseState.value?.let { response ->
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(dateInMillis))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formattedDate,
                    fontSize = 25.sp,
                    color = Color(0xFF699DFF),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            val hourlyForecasts = response.list.filter {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it.dt * 1000)) == formattedDate
            }.sortedBy { it.dt }

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                hourlyForecasts.forEach { weather ->
                    val kelvinToCelsius: (Double) -> Int = { (it - 273.15).toInt() }
                    val formattedTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(weather.dt * 1000))

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = formattedTime,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF699DFF)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${kelvinToCelsius(weather.main.temp)}°C",
                            fontSize = 20.sp,
                            color = Color(0xFF656565),
                            modifier = Modifier.weight(1f)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                        weather.weather.firstOrNull()?.icon?.let { iconName ->
                            WeatherIcon(
                                iconCode = iconName,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color(0xFF699DFF)
                    )
                }
            }
        }
    }
}
