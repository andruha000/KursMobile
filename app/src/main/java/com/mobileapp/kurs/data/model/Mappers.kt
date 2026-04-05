package com.mobileapp.kurs.data.model

import com.mobileapp.kurs.data.dto.geo.GeoDto
import com.mobileapp.kurs.data.dto.weather.WeatherResponse
import com.mobileapp.kurs.data.entity.CityEntity

fun CityEntity.toModel(): City = City(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    region = region,
    country = country
)

fun City.toEntity(): CityEntity = CityEntity(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    region = region,
    country = country
)

fun GeoDto.toModel(): City = City(
    id = 0,
    name = name,
    latitude = latitude,
    longitude = longitude,
    region = region,
    country = country
)

fun WeatherResponse.toModel(): WeatherForecast {
    val current = requireNotNull(currentWeather) { "Current weather is missing" }
    val hourly = requireNotNull(hourlyForecast) { "Hourly forecast is missing" }
    val daily = requireNotNull(dailyForecast) { "Daily forecast is missing" }

    return WeatherForecast(
        current = CurrentWeather(
            time = current.time,
            temperature = current.temperature,
            humidity = current.humidity,
            weatherCode = current.weatherCode,
            windSpeed = current.windSpeed
        ),
        hourly = hourly.time.indices.map { index ->
            HourlyWeather(
                time = hourly.time[index],
                temperature = hourly.temperature.getOrElse(index) { 0.0 },
                weatherCode = hourly.weatherCode.getOrElse(index) { 0 }
            )
        },
        daily = daily.time.indices.map { index ->
            WeatherDay(
                date = daily.time[index],
                weatherCode = daily.weatherCode.getOrElse(index) { 0 },
                temperatureMax = daily.temperatureMax.getOrElse(index) { 0.0 },
                temperatureMin = daily.temperatureMin.getOrElse(index) { 0.0 }
            )
        }
    )
}
