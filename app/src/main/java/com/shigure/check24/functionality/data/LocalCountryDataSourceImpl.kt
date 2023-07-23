package com.shigure.check24.functionality.data

import com.shigure.check24.app.App
import com.shigure.check24.functionality.data.local.CountryDataBase
import com.shigure.check24.functionality.data.local.toDataBaseEntity
import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.entity.CountryName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalCountryDataSourceImpl : LocalCountryDataSource {

    private val dao = CountryDataBase.create(App.get()).countryDao()
    override fun getAllSavedCountries(): Flow<List<CountryName>> =
        dao.getAllCountries().map { countryEntities ->
            if (countryEntities.isEmpty()) {
                listOf()
            } else {
                countryEntities.map {
                     CountryName(it.name)
                }
            }
        }

    override suspend fun saveCountry(country: Country) {
        withContext(Dispatchers.IO) {
            dao.insertCountry(country.toDataBaseEntity())
        }
    }

    override suspend fun deleteCountry(country: CountryName) {
        withContext(Dispatchers.IO) {
            dao.deleteCountry(country.name)
        }
    }
}