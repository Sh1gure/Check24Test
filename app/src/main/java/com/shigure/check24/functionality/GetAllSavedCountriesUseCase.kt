package com.shigure.check24.functionality

import com.shigure.check24.functionality.repository.CountriesRepository
import com.shigure.check24.functionality.repository.CountriesRepositoryImpl

class GetAllSavedCountriesUseCase {

    private val countriesRepository: CountriesRepository = CountriesRepositoryImpl()

    operator fun invoke() = countriesRepository.allCountriesFlow

}