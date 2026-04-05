package com.mobileapp.kurs.ui.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R

@StringRes
fun getWeatherIconRes(weatherCode: Int): Int {
    return when (weatherCode) {
        0, 1 -> R.string.weather_icon_clear
        2 -> R.string.weather_icon_partly_cloudy
        3 -> R.string.weather_icon_cloudy
        45, 48 -> R.string.weather_icon_fog
        51, 53, 55 -> R.string.weather_icon_drizzle
        61, 63, 65 -> R.string.weather_icon_rain
        71, 73, 75 -> R.string.weather_icon_snow
        80, 81, 82 -> R.string.weather_icon_shower
        95, 96, 99 -> R.string.weather_icon_thunderstorm
        else -> R.string.weather_icon_unknown
    }
}

@StringRes
fun getWeatherDescriptionRes(weatherCode: Int): Int {
    return when (weatherCode) {
        0, 1 -> R.string.weather_desc_clear
        2 -> R.string.weather_desc_partly_cloudy
        3 -> R.string.weather_desc_cloudy
        45, 48 -> R.string.weather_desc_fog
        51, 53, 55 -> R.string.weather_desc_drizzle
        61, 63, 65 -> R.string.weather_desc_rain
        71, 73, 75 -> R.string.weather_desc_snow
        80, 81, 82 -> R.string.weather_desc_shower
        95, 96, 99 -> R.string.weather_desc_thunderstorm
        else -> R.string.weather_desc_unknown
    }
}

@Composable
fun getWeatherIcon(weatherCode: Int): String = stringResource(getWeatherIconRes(weatherCode))

@Composable
fun getWeatherDescription(weatherCode: Int): String = stringResource(getWeatherDescriptionRes(weatherCode))