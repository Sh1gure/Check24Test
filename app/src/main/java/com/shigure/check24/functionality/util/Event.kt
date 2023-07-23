package com.shigure.check24.functionality.util

sealed interface Event<out T> {

    sealed interface Initial : Event<Nothing>

    object Loading : Event<Nothing>

    data class Success<out T>(
        val data: T,
        val error: String? = null
    ) : Event<T>

    @JvmInline
    value class Error(val error: String) : Event<Nothing>
}

val Event<*>.isLoading
    get() = this is Event.Loading

val Event<*>.error: String?
    get() = if (this is Event.Error) {
        this.error
    } else {
        null
    }