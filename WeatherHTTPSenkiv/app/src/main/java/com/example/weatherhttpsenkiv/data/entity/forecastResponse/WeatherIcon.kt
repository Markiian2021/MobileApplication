package com.example.weatherhttpsenkiv.data.entity.forecastResponse

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherhttpsenkiv.R


@Composable
fun WeatherIcon(iconCode: String, modifier: Modifier = Modifier) {
    val iconName = when (iconCode) {
        "01d" -> R.drawable.icon_01d
        "01n" -> R.drawable.icon_01d
        "02d" -> R.drawable.icon_02d
        "02n" -> R.drawable.icon_02d
        "03d" -> R.drawable.icon_03d
        "03n" -> R.drawable.icon_03d
        "04d" -> R.drawable.icon_04d
        "04n" -> R.drawable.icon_04d
        "09d" -> R.drawable.icon_09d
        "09n" -> R.drawable.icon_09d
        "10d" -> R.drawable.icon_10d
        "10n" -> R.drawable.icon_10d
        "11d" -> R.drawable.icon_11d
        "11n" -> R.drawable.icon_11d
        "13d" -> R.drawable.icon_13d
        "13n" -> R.drawable.icon_13d
        "50d" -> R.drawable.icon_50d
        "50n" -> R.drawable.icon_50d
        else -> R.drawable.icon_default
    }

    Image(
        painter = painterResource(id = iconName),
        contentDescription = null,
        modifier = modifier
    )
}
