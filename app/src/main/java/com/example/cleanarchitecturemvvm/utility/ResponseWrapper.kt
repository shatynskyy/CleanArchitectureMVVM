package com.example.cleanarchitecturemvvm.utility

sealed class ResponseWrapper<out T> {
    class Loading<out T> : ResponseWrapper<T>()
    class Success<out T>(val data: T) : ResponseWrapper<T>()
    class Error<out T>(val exception: Throwable) : ResponseWrapper<T>()
}