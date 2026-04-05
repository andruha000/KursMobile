package com.mobileapp.kurs.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.data.model.WeatherDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DailyForecastSection(
    dailyItems: List<WeatherDay>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_radius_large)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column {
            Text(
                text = stringResource(R.string.section_daily_forecast),
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.space_large_2),
                    top = dimensionResource(R.dimen.space_large_2),
                    end = dimensionResource(R.dimen.space_large_2),
                    bottom = dimensionResource(R.dimen.space_medium_3)
                ),
                style = MaterialTheme.typography.titleMedium
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))

            dailyItems.forEachIndexed { index, day ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(R.dimen.space_large_2),
                            vertical = dimensionResource(R.dimen.space_medium_3)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (index == 0) {
                            stringResource(R.string.label_today)
                        } else {
                            formatDayLabel(day.date)
                        },
                        modifier = Modifier.width(dimensionResource(R.dimen.weather_card_info_height)),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = getWeatherIcon(day.weatherCode),
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.space_medium_1))
                            .width(dimensionResource(R.dimen.space_large_3)),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.format_temperature, day.temperatureMax.toInt()),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = " ${stringResource(R.string.format_temperature, day.temperatureMin.toInt())}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                if (index != dailyItems.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.space_large_2)),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    )
                }
            }
        }
    }
}

private fun formatDayLabel(value: String): String {
    return LocalDate.parse(value)
        .format(DateTimeFormatter.ofPattern("EEE"))
        .replaceFirstChar { it.uppercase() }
}
