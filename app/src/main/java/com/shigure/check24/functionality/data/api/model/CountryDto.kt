package com.shigure.check24.functionality.data.api.model

import com.shigure.check24.functionality.entity.Country
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

private const val RIGHT_CAR_SIDE_WRITTEN = "right"

@JsonClass(generateAdapter = true)
class CountryDto(
    @Json(name = "flags") var flag: CountryFlagDto,
    @Json(name = "name") var countryName: CountryNameDto,
    @Json(name = "car") var carSide: CarDto,
//    @Json(name = "languages") var countryLanguages: CountryLanguagesDto,
//    @Json(name = "currency") var currency: CountryLanguagesDto,
    @Json(name = "population") var population: Int,
)

@JsonClass(generateAdapter = true)
data class CountryFlagDto(
    @Json(name = "png") var png: String
)

@JsonClass(generateAdapter = true)
data class CarDto(
    @Json(name = "side") var side: String
)

@JsonClass(generateAdapter = true)
data class CountryNameDto(
    @Json(name = "official") var name: String
)

@JsonClass(generateAdapter = true)
data class CountryLanguagesDto(
    @Json(name = "languages") var languages: Map<String, String>
)

internal fun CountryDto.toEntity(): Country =
    Country(
        flag = this.flag.png,
        name = this.countryName.name,
        population = this.population,
        isCarSideRight = this.carSide.side == RIGHT_CAR_SIDE_WRITTEN
//        languages = this.countryLanguages.languages.map { it.value },
    )

