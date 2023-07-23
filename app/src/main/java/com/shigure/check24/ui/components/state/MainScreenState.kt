package com.shigure.check24.ui.components.state

import com.shigure.check24.functionality.entity.Country

sealed interface MainScreenState {

    sealed interface Initial : MainScreenState {
        object Loading : Initial
        object Empty : Initial
    }

    sealed interface Content : MainScreenState {
        data class Error(val error: String) : MainScreenState
        data class Data(
            val countries: List<Country>,
            val error: Set<String>,
            val isLoading: Boolean,
        ) : MainScreenState
    }

    // TODO low on time, no time to implement navigation
    sealed interface Details : MainScreenState {
        data class Data(
            val country: Country
        ) : MainScreenState
    }
}