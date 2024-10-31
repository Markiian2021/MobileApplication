package com.example.weatherhttpsenkiv.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherhttpsenkiv.R
import com.example.weatherhttpsenkiv.data.entity.forecastResponse.WeatherIcon
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherScreen(
    onWeatherScreen: (Long) -> Unit,
    viewModel: WeatherScreenViewModel = getViewModel()
) {

    val weatherResponseState = viewModel.weatherResponseStateFlow.collectAsState()
    val weatherForecastResponseState = viewModel.weatherForecastResponseStateFlow.collectAsState()

    val kelvinToCelsius: (Double) -> Int = { (it - 273.15).toInt() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xF2CCCCCC))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.ic_meteorology),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Погода",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF656565)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(8.dp, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.image_cloud),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .background(color = Color(0x4DE3E3E3))
                        .fillMaxSize()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "${kelvinToCelsius(weatherResponseState.value?.main?.temp ?: 0.0)}°C",
                                fontSize = 70.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Text(
                                text = "Min: ${kelvinToCelsius(weatherResponseState.value?.main?.temp_min ?: 0.0)}°C Max: ${kelvinToCelsius(weatherResponseState.value?.main?.temp_max ?: 0.0)}°C",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        weatherForecastResponseState.value?.list?.firstOrNull()?.weather?.firstOrNull()?.icon?.let { iconName ->
                            WeatherIcon(iconCode = iconName, modifier = Modifier.size(140.dp).align(Alignment.CenterVertically))
                        }

                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xE5FFFFFF),
                        )
                        .padding(20.dp, 10.dp, 20.dp, 10.dp)
                ) {
                    Text(
                        text = "${weatherResponseState.value?.name}, ${weatherResponseState.value?.sys?.country}",
                        fontSize = 25.sp,
                        color = Color(0xFF656565)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp),
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                    Text(
                        text = "Погода сьогодні в ${weatherResponseState.value?.name}, ${weatherResponseState.value?.sys?.country}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF656565)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "FEELS LIKE",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF699DFF)
                    )
                    Text(
                        text = "${kelvinToCelsius(weatherResponseState.value?.main?.feels_like ?: 0.0)}°C",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF656565)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Temp:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${kelvinToCelsius(weatherResponseState.value?.main?.temp ?: 0.0)}°C",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Feels Like:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${kelvinToCelsius(weatherResponseState.value?.main?.feels_like ?: 0.0)}°C",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Temp min:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${kelvinToCelsius(weatherResponseState.value?.main?.temp_min ?: 0.0)}°C",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Temp max:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${kelvinToCelsius(weatherResponseState.value?.main?.temp_max ?: 0.0)}°C",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                    }
                    VerticalDivider(
                        modifier = Modifier.height(100.dp),
                        thickness = 2.dp,
                        color = Color(0xFF699DFF)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Pressure:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${weatherResponseState.value?.main?.pressure}",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Humidity:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${weatherResponseState.value?.main?.humidity}",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Sea level:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${weatherResponseState.value?.main?.sea_level}",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Grnd level:",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Text(
                                text = "${weatherResponseState.value?.main?.grnd_level}",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF656565)
                            )
                        }
                    }
                }
            }
        }

        val dailyForecasts = weatherForecastResponseState.value?.list
            ?.groupBy {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                sdf.format(Date(it.dt * 1000))
            }
            ?.map { entry -> entry.value.first() }
            ?.take(4)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(15.dp),
            ) {
                Text(
                    text = "Щоденний прогноз в ${weatherResponseState.value?.name}, ${weatherResponseState.value?.sys?.country}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF656565)
                )
                Spacer(modifier = Modifier.height(10.dp))

                dailyForecasts?.forEachIndexed { index, forecast ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onWeatherScreen(forecast.dt * 1000) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (index) {
                                0 -> "Сьогодні"
                                1 -> "Завтра"
                                2 -> "Після завтра"
                                3 -> "Через три дні"
                                else -> ""
                            },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF699DFF),
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "${(forecast.main.temp - 273.15).toInt()}°C",
                            fontSize = 20.sp,
                            color = Color(0xFF656565),
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                        forecast.weather.firstOrNull()?.icon?.let { iconName ->
                            WeatherIcon(iconCode = iconName, modifier = Modifier.size(50.dp))
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