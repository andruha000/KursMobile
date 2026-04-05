package com.mobileapp.kurs.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.data.model.toModel
import com.mobileapp.kurs.viewmodel.SearchUiState
import com.mobileapp.kurs.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onAddCity: (com.mobileapp.kurs.data.model.City) -> Unit,
    onBack: () -> Unit
) {
    val query by viewModel.query.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.space_large_1)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_medium_2))
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = viewModel::onQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.label_city)) },
            singleLine = true
        )

        when (val state = uiState) {
            SearchUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            SearchUiState.Error -> {
                Text(
                    text = stringResource(R.string.load_error),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            SearchUiState.Idle -> {
                Text(
                    text = stringResource(R.string.hint_start_typing),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            SearchUiState.Empty -> {
                Text(
                    text = stringResource(R.string.cities_not_found),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            is SearchUiState.Content -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small_3))) {
                    items(state.cities, key = { it.id }) { city ->
                        ListItem(
                            headlineContent = { Text(city.name) },
                            supportingContent = {
                                Text(
                                    listOfNotNull(city.region, city.country).joinToString(stringResource(R.string.separator))
                                )
                            },
                            modifier = Modifier.clickable {
                                onAddCity(city.toModel())
                                viewModel.reset()
                                onBack()
                            }
                        )
                    }
                }
            }
        }
    }
}
