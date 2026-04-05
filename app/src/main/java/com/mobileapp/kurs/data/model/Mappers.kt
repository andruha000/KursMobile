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

fun WeatherResponse.toModel(): WeatherForecast? {
    val current = currentWeather ?: return null
    val hourly = hourlyForecast ?: return null
    val daily = dailyForecast ?: return null

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
                temperature = hourly.temperature[index],
                weatherCode = hourly.weatherCode[index]
            )
        },
        daily = daily.time.indices.map { index ->
            WeatherDay(
                date = daily.time[index],
                weatherCode = daily.weatherCode[index],
                temperatureMax = daily.temperatureMax[index],
                temperatureMin = daily.temperatureMin[index]
            )
        }
    )
}
