package com.mobileapp.kurs.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.ui.components.CurrentWeatherCard
import com.mobileapp.kurs.ui.components.DailyForecastSection
import com.mobileapp.kurs.ui.components.HourlyForecastSection
import com.mobileapp.kurs.viewmodel.MainUiState
import com.mobileapp.kurs.viewmodel.MainViewModel

@Composable
fun CityScreen(
    cityId: Int,
    viewModel: MainViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val contentState = uiState as? MainUiState.Content
    val item = contentState?.items?.firstOrNull { it.city.id == cityId }

    if (item == null) {
        Text(
            text = stringResource(R.string.weather_load_error),
            modifier = Modifier.padding(dimensionResource(R.dimen.space_large_1)),
            style = MaterialTheme.typography.bodyLarge
        )
        return
    }

    val forecast = item.forecast
    val today = forecast.daily.first()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.space_large_1)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_large_1))
    ) {
        item {
            CurrentWeatherCard(
                current = forecast.current,
                today = today
            )
        }

        item {
            HourlyForecastSection(
                hourlyItems = forecast.hourly,
                currentTime = forecast.current.time
            )
        }

        item {
            DailyForecastSection(dailyItems = forecast.daily)
        }
    }
}
