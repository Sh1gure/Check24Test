package com.shigure.check24.functionality.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


private const val DB_NAME = "countries_database"

@Database(entities = [(CountryEntity::class)], version = 1)
abstract class CountryDataBase : RoomDatabase() {

    abstract fun countryDao(): CountriesDao

    companion object {
        fun create(context: Context): CountryDataBase {

            return Room.databaseBuilder(
                context,
                CountryDataBase::class.java,
                DB_NAME
            ).build()
        }
    }

}