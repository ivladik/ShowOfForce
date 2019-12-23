package com.example.feature_tours.presentation.model

import com.example.core.presentation.model.State

class Response<T> private constructor(
    val state: State,
    val data: T? = null,
    val throwable: Throwable? = null
) { // TODO: sealed?

    companion object {

        @JvmStatic
        fun <T> createLoadingInstance(): Response<T> {
            return Response(State.LOADING)
        }

        @JvmStatic
        fun <T> createDoneInstance(data: T): Response<T> {
            return Response(State.DONE, data)
        }

        @JvmStatic
        fun <T> createErrorInstance(throwable: Throwable): Response<T> {
            return Response(State.ERROR, throwable = throwable)
        }
    }
}