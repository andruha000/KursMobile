package com.mobileapp.kurs.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobileapp.kurs.data.repository.CityRepository

object SearchViewModelFactory {

    fun create(
        cityRepository: CityRepository
    ): ViewModelProvider.Factory = viewModelFactory {
        initializer {
            SearchViewModel(cityRepository)
        }
    }
}
