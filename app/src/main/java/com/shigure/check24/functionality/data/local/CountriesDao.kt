package com.shigure.check24.functionality.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<CountryEntity>>

    @Insert
    fun insertCountry(country: CountryEntity)

    @Query("DELETE FROM countries WHERE name = :country")
    fun deleteCountry(country: String)

}