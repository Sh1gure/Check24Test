package com.shigure.check24.functionality.data.api

import com.shigure.check24.functionality.data.api.model.CountryDto
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {

    @GET("all?fields=name,currencies,languages,flags,population,car")
    suspend fun getAllCountries(): Response<List<CountryDto>>
}
