package com.mobileapp.kurs.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.data.model.WeatherDay
import com.mobileapp.kurs.data.model.CurrentWeather

@Composable
fun CurrentWeatherCard(
    current: CurrentWeather,
    today: WeatherDay,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_radius_large)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.space_large_3)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_medium_1))
        ) {
            Text(
                text = stringResource(R.string.format_temperature, current.temperature.toInt()),
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(
                    R.string.format_temperature_pair,
                    today.temperatureMax.toInt().toString(),
                    today.temperatureMin.toInt().toString()
                ),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = getWeatherDescription(current.weatherCode),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(R.string.format_wind_speed, current.windSpeed),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.format_humidity, current.humidity),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
