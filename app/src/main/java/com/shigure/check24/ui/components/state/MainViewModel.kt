package com.shigure.check24.ui.components.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shigure.check24.functionality.DeleteVisitedCountryUseCase
import com.shigure.check24.functionality.GetAllCountriesUseCase
import com.shigure.check24.functionality.GetAllSavedCountriesUseCase
import com.shigure.check24.functionality.SaveVisitedCountryUseCase
import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.entity.CountryName
import com.shigure.check24.functionality.util.Event
import com.shigure.check24.functionality.util.error
import com.shigure.check24.functionality.util.isLoading
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel : ViewModel() {

    private val getAllSavedCountriesUseCase = GetAllSavedCountriesUseCase()
    private val saveVisitedCountryUseCase = SaveVisitedCountryUseCase()
    private val deleteVisitedCountryUseCase = DeleteVisitedCountryUseCase()
    private val getAllCountriesUseCase = GetAllCountriesUseCase()

    private val selectedCountry = MutableStateFlow<Country?>(null)

    // TODO: Would be good to extract in separate extension implementation
    private val refreshableCountries = MutableSharedFlow<Unit>(replay = 1).apply {
        tryEmit(Unit)
    }
    private val refreshableSavedCountries = MutableSharedFlow<Unit>(replay = 1).apply {
        tryEmit(Unit)
    }
    private val submittableSave = MutableSharedFlow<Unit>(replay = 1)
    private val submittableDelete = MutableSharedFlow<Unit>(replay = 1)

    private val savedCountriesFlow: Flow<List<CountryName>> =
        refreshableSavedCountries.flatMapLatest {
            getAllSavedCountriesUseCase()
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(),
            initialValue = listOf()
        )

    private val countriesFlow: Flow<Event<Map<CountryName, Country>>> =
        refreshableCountries.flatMapLatest {
            getAllCountriesUseCase()
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(),
            initialValue = Event.Loading
        )

    private val saveFlow = submittableSave.flatMapLatest {
        // TODO no unwrap. in separate VM this problem will solve itself
        saveVisitedCountryUseCase.invoke(selectedCountry.value!!)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(),
        initialValue = Event.Loading
    ).onEach {
        refresh()
    }

    private val deleteFlow = submittableDelete.flatMapLatest {
        val countryName = CountryName(selectedCountry.value!!.name)
        deleteVisitedCountryUseCase(countryName)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(),
        initialValue = Event.Loading
    ).onEach {
        refresh()
    }

    val uiState: StateFlow<MainScreenState> = combine(
        savedCountriesFlow,
        countriesFlow,
        selectedCountry,
        saveFlow,
        deleteFlow
    ) { saved, received, country, _, _ ->
        val isLoading = received.isLoading
        val error = received.error
        MainScreenState.Initial.Loading
        if (country != null) {
            val updatedCountry: Country? = if (received is Event.Success) {
                checkSaved(received.data, CountryName(country.name))
            } else {
                null
            }
            MainScreenState.Details.Data(updatedCountry ?: country)
        } else {
            when (received) {
                Event.Loading -> {
                    MainScreenState.Initial.Loading
                }

                is Event.Error -> {
                    MainScreenState.Content.Error(error = received.error)
                }

                is Event.Success -> {
                    val setErrors = mutableSetOf<String>()
                    error?.let {
                        setErrors.add(it)
                    }
                    received.error?.let {
                        setErrors.add(it)
                    }
                    MainScreenState.Content.Data(
                        countries = checkSaved(received.data, saved),
                        error = setErrors,
                        isLoading = isLoading
                    )
                }
            }
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(),
            initialValue = MainScreenState.Initial.Loading
        )

    fun refresh() {
        viewModelScope.launch {
            refreshableCountries.emit(Unit)
            refreshableSavedCountries.emit(Unit)
        }
    }

    fun save() {
        submittableSave.tryEmit(Unit)
    }

    fun delete() {
        submittableDelete.tryEmit(Unit)
    }

    fun onItemClicked(country: Country) {
        selectedCountry.value = country
    }

    fun onBackPressed() {
        selectedCountry.value = null
    }

    private fun checkSaved(
        received: Map<CountryName, Country>, saved: List<CountryName>
    ): List<Country> {
        val map = received.toMutableMap()
        saved.forEach { countryName ->
            map[countryName]?.let {
                map.replace(countryName, it.copy(isSaved = true))
            }
        }
        return map.values.toList()
    }

    private fun checkSaved(
        received: Map<CountryName, Country>, saved: CountryName
    ): Country? {
        return received[saved]?.copy(isSaved = true)
    }

}