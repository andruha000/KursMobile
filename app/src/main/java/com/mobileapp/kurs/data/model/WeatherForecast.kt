package com.mobileapp.kurs.data.model

data class WeatherForecast(
    val current: CurrentWeather,
    val hourly: List<HourlyWeather>,
    val daily: List<WeatherDay>
)

data class CurrentWeather(
    val time: String,
    val temperature: Double,
    val humidity: Int,
    val weatherCode: Int,
    val windSpeed: Double
)

data class HourlyWeather(
    val time: String,
    val temperature: Double,
    val weatherCode: Int
)

data class WeatherDay(
    val date: String,
    val weatherCode: Int,
    val temperatureMax: Double,
    val temperatureMin: Double
)
