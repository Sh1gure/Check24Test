package com.shigure.check24.functionality.data.api

import com.shigure.check24.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder().build()
            it.proceed(request)
        }



    val countryClient: CountryApiService = Retrofit.Builder()
        .client(okHttpClient.build())
        .baseUrl(BuildConfig.URL_COUNTRIES)
        .addConverterFactory(
            MoshiConverterFactory
                .create()
                .asLenient()
        )
        .build()
        .create(CountryApiService::class.java)

}