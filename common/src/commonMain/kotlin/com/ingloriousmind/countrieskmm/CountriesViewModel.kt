package com.ingloriousmind.countrieskmm

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface CountriesViewModelInputs {
    fun onReloadCountries()
    fun onCountryClicked(country: CountriesQuery.Country)

    companion object {
        val NONE = object : CountriesViewModelInputs {
            override fun onReloadCountries() = Unit
            override fun onCountryClicked(country: CountriesQuery.Country) = Unit
        }
    }
}

open class CountriesViewModel : KMMViewModel(), KoinComponent, CountriesViewModelInputs {
    private val countriesRepo by inject<CountriesRepositoryType>()

    private val _uiState = MutableStateFlow(viewModelScope, CountriesUiState())

    @NativeCoroutinesState
    val uiState: StateFlow<CountriesUiState>
        get() = _uiState.asStateFlow()

    override fun onReloadCountries() {
        viewModelScope.coroutineScope.launch {
            _uiState.update { it.copy(loading = true) }

            countriesRepo.fetchCountries()
                .sortedBy { it.name }
                .onEach { L.d("  $it") }
                .let { countries -> _uiState.update { it.copy(countries = countries, loading = false) } }
        }
    }

    override fun onCountryClicked(country: CountriesQuery.Country) {
        // TODO
        L.d("clicked: $country")
    }
}

data class CountriesUiState(
    val countries: List<CountriesQuery.Country> = emptyList(),
    val loading: Boolean = false,
)
