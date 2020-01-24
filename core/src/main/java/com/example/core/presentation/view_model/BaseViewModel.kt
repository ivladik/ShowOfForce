package com.example.core.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.core.domain.model.NavigationCommand
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposableUntilCleared = CompositeDisposable() // TODO: untilDetach + lifecycle component?

    private val navigationCommands = MutableLiveData<NavigationCommand>()

    fun getNavigationCommands(): LiveData<NavigationCommand> {
        return navigationCommands
    }

    fun navigate(directions: NavDirections) {
        navigationCommands.postValue(NavigationCommand.To(directions))
    }

    protected fun Disposable.untilCleared() {
        compositeDisposableUntilCleared.add(this)
    }

    override fun onCleared() {
        compositeDisposableUntilCleared.clear()
        super.onCleared()
    }
}