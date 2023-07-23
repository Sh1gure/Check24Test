package com.shigure.check24.functionality.util

sealed interface DBEvent {
    object Loading : DBEvent
    object Success : DBEvent
}