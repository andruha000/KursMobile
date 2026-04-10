package com.mobileapp.kurs.data.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private val client = OkHttpClient.Builder().build()

    private fun retrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val geoApi: GeoApi by lazy {
        retrofit("https://geocoding-api.open-meteo.com/").create(GeoApi::class.java)
    }

    val weatherApi: WeatherApi by lazy {
        retrofit("https://api.open-meteo.com/").create(WeatherApi::class.java)
    }
}
