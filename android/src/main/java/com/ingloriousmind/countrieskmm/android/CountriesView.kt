package com.ingloriousmind.countrieskmm.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ingloriousmind.countrieskmm.CountriesQuery
import com.ingloriousmind.countrieskmm.CountriesUiState
import com.ingloriousmind.countrieskmm.CountriesViewModel
import com.ingloriousmind.countrieskmm.CountriesViewModelInputs

@Composable
fun CountriesScreen() {
    val viewModel = viewModel<CountriesViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.onReloadCountries()
    }

    CountriesView(state = state, inputs = viewModel)
}

@Composable
private fun CountriesView(state: CountriesUiState, inputs: CountriesViewModelInputs) {
    if (!state.loading) {
        LazyColumn {
            items(state.countries) { country ->
                CountryItem(country, inputs)
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun CountryItem(country: CountriesQuery.Country, inputs: CountriesViewModelInputs) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { inputs.onCountryClicked(country) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = country.emoji,
            style = MaterialTheme.typography.headlineLarge,
        )

        Column {
            Text(
                text = "${country.name} (${country.native})",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = country.continent.name,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
private fun CountriesViewPreview() {
    val previewState = CountriesUiState(
        countries = listOf(
            CountriesQuery.Country(
                "CH",
                "Switzerland",
                "",
                "",
                CountriesQuery.Continent("", "Europe"),
                null,
                emptyList(),
                "\uD83C\uDDE8\uD83C\uDDED",
                ""
            )
        ),
        loading = false,
    )

    MyApplicationTheme {
        Surface {
            CountriesView(state = previewState, inputs = CountriesViewModelInputs.NONE)
        }
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    val previewState = CountriesUiState(
        countries = emptyList(),
        loading = true,
    )

    MyApplicationTheme {
        Surface {
            CountriesView(state = previewState, inputs = CountriesViewModelInputs.NONE)
        }
    }
}
