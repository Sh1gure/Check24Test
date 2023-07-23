package com.shigure.check24.functionality.repository

import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.entity.CountryName
import com.shigure.check24.functionality.util.DBEvent
import com.shigure.check24.functionality.util.Event
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    val allCountriesFlow: Flow<List<CountryName>>

    suspend fun requestAllCountries() : Flow<Event<Map<CountryName, Country>>>

    suspend fun saveCountry(country: Country) : Flow<DBEvent>

    suspend fun deleteCountry(country: CountryName) : Flow<DBEvent>

}