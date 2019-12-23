package com.example.core.presentation.view_model

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposableUntilCleared = CompositeDisposable() // TODO: untilDetach + lifecycle component?

    protected fun Disposable.untilCleared() {
        compositeDisposableUntilCleared.add(this)
    }

    override fun onCleared() {
        compositeDisposableUntilCleared.clear()
        super.onCleared()
    }
}