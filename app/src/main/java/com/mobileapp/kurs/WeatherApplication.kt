package com.mobileapp.kurs

import android.app.Application
import com.mobileapp.kurs.data.db.WeatherDatabase
import com.mobileapp.kurs.data.net.ApiFactory
import com.mobileapp.kurs.data.repository.CityRepository
import com.mobileapp.kurs.data.repository.WeatherRepository

class WeatherApplication : Application() {

    val database: WeatherDatabase by lazy {
        WeatherDatabase.getDatabase(this)
    }

    val cityRepository: CityRepository by lazy {
        CityRepository(
            cityDao = database.cityDao(),
            geoApi = ApiFactory.geoApi
        )
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(weatherApi = ApiFactory.weatherApi)
    }
}
