package com.example.core.util

import io.reactivex.FlowableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxSchedulersUtil {

    fun <T> getIOToMainFlowable(): FlowableTransformer<T, T> = FlowableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> getComputationToMainFlowable(): FlowableTransformer<T, T> = FlowableTransformer {
        it.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> getIOToMainSingle(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> getComputationToMainSingle(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}