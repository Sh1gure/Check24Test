package com.shigure.check24.functionality.data

import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.entity.CountryName
import kotlinx.coroutines.flow.Flow

interface LocalCountryDataSource {

    fun getAllSavedCountries(): Flow<List<CountryName>>

    suspend fun saveCountry(country: Country)

    suspend fun deleteCountry(country: CountryName)

}