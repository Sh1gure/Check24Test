package com.shigure.check24.functionality

import com.shigure.check24.functionality.entity.Country
import com.shigure.check24.functionality.repository.CountriesRepository
import com.shigure.check24.functionality.repository.CountriesRepositoryImpl

class SaveVisitedCountryUseCase {

    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl()

    suspend operator fun invoke(country: Country) = countriesRepository.saveCountry(country)
}