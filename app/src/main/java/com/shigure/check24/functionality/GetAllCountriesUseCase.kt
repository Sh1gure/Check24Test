package com.shigure.check24.functionality

import com.shigure.check24.functionality.repository.CountriesRepository
import com.shigure.check24.functionality.repository.CountriesRepositoryImpl

class GetAllCountriesUseCase {

    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl()

    suspend operator fun invoke() = countriesRepository.requestAllCountries()

}