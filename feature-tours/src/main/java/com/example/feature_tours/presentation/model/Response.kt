package com.example.feature_tours.presentation.model

sealed class Response<out T> {

    class Done<out T>(val data: T) : Response<T>()

    class Error(val throwable: Throwable? = null) : Response<Nothing>()

    object Loading : Response<Nothing>()
}