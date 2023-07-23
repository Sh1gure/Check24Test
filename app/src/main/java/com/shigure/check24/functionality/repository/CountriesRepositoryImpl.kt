package com.shigure.check24.functionality.repository

import com.shigure.check24.functionality.data.LocalCountryDataSource
import com.shigure.check24.functionality.data.LocalCountryDataSourceImpl
import com.shigure.check24.functionality.data.CountryDataSource
import com.shigure.check24.functionality.data.CountryDataSourceImpl
import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.entity.CountryName
import com.shigure.check24.functionality.util.DBEvent
import com.shigure.check24.functionality.util.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountriesRepositoryImpl : CountriesRepository {

    private val dataSource: CountryDataSource = CountryDataSourceImpl()
    private val localDataSource: LocalCountryDataSource = LocalCountryDataSourceImpl()

    override val allCountriesFlow: Flow<List<CountryName>>
        get() =
            localDataSource.getAllSavedCountries()


    override suspend fun requestAllCountries(): Flow<Event<Map<CountryName, Country>>> = flow {
        emit(Event.Loading)
        val response = dataSource.getAllCountries()
        emit(response)
    }

    override suspend fun saveCountry(country: Country): Flow<DBEvent> = flow {
        emit(DBEvent.Loading)
        localDataSource.saveCountry(country)
        emit(DBEvent.Success)
    }

    override suspend fun deleteCountry(country: CountryName): Flow<DBEvent> = flow {
        emit(DBEvent.Loading)
        localDataSource.deleteCountry(country)
        emit(DBEvent.Success)
    }
}

