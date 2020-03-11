package com.alexiscrack3.booster

sealed class Resource<T> {
    class Success<T>(val value: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    class Failure<T>(val error: Throwable) : Resource<T>()
}
