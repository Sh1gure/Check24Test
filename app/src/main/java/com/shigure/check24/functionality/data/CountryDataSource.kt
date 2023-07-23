package com.shigure.check24.functionality.data

import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.entity.CountryName
import com.shigure.check24.functionality.util.Event

interface CountryDataSource {

    suspend fun getAllCountries(): Event<Map<CountryName, Country>>

}