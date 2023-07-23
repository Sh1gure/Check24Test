package com.shigure.check24.functionality.entity

data class Country(
    val flag: String,
    val name: String,
//    val languages: List<String>,
    val population: Int,
    val isCarSideRight: Boolean,
    val isSaved: Boolean = false,
)