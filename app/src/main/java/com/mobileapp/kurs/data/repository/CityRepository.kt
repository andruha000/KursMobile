package com.mobileapp.kurs.data.repository

import com.mobileapp.kurs.data.dao.CityDao
import com.mobileapp.kurs.data.dto.geo.GeoDto
import com.mobileapp.kurs.data.entity.CityEntity
import com.mobileapp.kurs.data.net.GeoApi
import kotlinx.coroutines.flow.Flow

class CityRepository(
    private val cityDao: CityDao,
    private val geoApi: GeoApi
) {
    fun getAllCities(): Flow<List<CityEntity>> {
        return cityDao.getAllCities()
    }
    suspend fun searchCities(query: String, count: Int = 6): List<GeoDto> {
        return geoApi.searchCities(name = query, count = count).results.orEmpty()
    }

    suspend fun addCity(city: CityEntity) {
        cityDao.insertCity(city)
    }

    suspend fun deleteCity(city: CityEntity) {
        cityDao.deleteCity(city)
    }
}
