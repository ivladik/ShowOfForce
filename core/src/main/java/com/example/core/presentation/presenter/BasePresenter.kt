package com.example.core.presentation.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    private val compositeDisposableDestroy = CompositeDisposable()
    private val compositeDisposableDetach = CompositeDisposable()

    override fun detachView(view: V) {
        compositeDisposableDetach.clear()
        super.detachView(view)
    }

    override fun onDestroy() {
        compositeDisposableDestroy.clear()
        super.onDestroy()
    }

    protected fun Disposable.untilDetach() {
        compositeDisposableDetach.add(this)
    }

    protected fun Disposable.untilDestroy() {
        compositeDisposableDestroy.add(this)
    }
}