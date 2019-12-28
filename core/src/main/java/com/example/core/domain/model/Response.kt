package com.example.core.domain.model

sealed class Response<out T> {

    class Done<out T>(val data: T) : Response<T>()

    class Error(val throwable: Throwable? = null) : Response<Nothing>()

    object Loading : Response<Nothing>()
}