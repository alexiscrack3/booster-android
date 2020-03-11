package com.alexiscrack3.booster.api

sealed class Resource<T> {
    data class Success<T>(val value: T) : Resource<T>()
    data class Loading<T>(val value: T? = null) : Resource<T>()
    data class Failure<T>(val error: Throwable, val value: T? = null) : Resource<T>()
}
