package com.shigure.check24.functionality

import com.shigure.check24.functionality.entity.CountryName
import com.shigure.check24.functionality.repository.CountriesRepository
import com.shigure.check24.functionality.repository.CountriesRepositoryImpl

class DeleteVisitedCountryUseCase {

    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl()

    suspend operator fun invoke(country: CountryName) = countriesRepository.deleteCountry(country)
}