package com.shigure.check24.functionality.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shigure.check24.functionality.entity.Country

const val TABLE_NAME = "countries"

@Entity(tableName = TABLE_NAME)
data class CountryEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)

fun Country.toDataBaseEntity() =
    CountryEntity(
        name = this.name,
    )
