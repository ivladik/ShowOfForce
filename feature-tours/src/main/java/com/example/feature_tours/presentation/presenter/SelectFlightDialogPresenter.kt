package com.example.feature_tours.presentation.presenter

import com.example.core.presentation.presenter.BasePresenter
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.presentation.ui.view.SelectFlightDialogView
import com.example.core.util.RxSchedulersUtil
import com.example.feature_tours.di.screen.select_flights.SelectFlightSubcomponent
import moxy.InjectViewState
import timber.log.Timber

@InjectViewState
class SelectFlightDialogPresenter(private val interactor: IToursInteractor) : BasePresenter<SelectFlightDialogView>() {

    fun loadAvailableEntireTours(tourId: Int) {
        // TODO: progress bar?
        interactor.loadAvailableEntireToursById(tourId)
            .compose(RxSchedulersUtil.getIOToMainSingle())
            .subscribe(
                {
                    viewState.showAvailableEntireTours(it)
                },
                {
                    Timber.e(it)
                }
            )
            .untilDestroy()
    }

    override fun onDestroy() {
        SelectFlightSubcomponent
            .release()
        super.onDestroy()
    }
}