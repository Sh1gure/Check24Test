package com.shigure.check24.functionality.data

import com.shigure.check24.functionality.data.api.RetrofitClient
import com.shigure.check24.functionality.data.api.model.toEntity
import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.entity.CountryName
import com.shigure.check24.functionality.util.Event

class CountryDataSourceImpl : CountryDataSource {

    private val api = RetrofitClient.countryClient
    override suspend fun getAllCountries(): Event<Map<CountryName, Country>> {
        val result = api.getAllCountries()
        return if (result.isSuccessful && result.body() != null) {
            result.body()?.let {
                Event.Success(it.associate { country ->
                    CountryName(country.countryName.name) to country.toEntity()
                }
                )
            } ?: Event.Error("Empty body")
        } else {
            Event.Error(result.message())
        }
    }
}