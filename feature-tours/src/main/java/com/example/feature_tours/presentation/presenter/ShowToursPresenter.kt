package com.example.feature_tours.presentation.presenter

import com.example.core.presentation.presenter.BasePresenter
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.presentation.ui.view.ShowToursView
import com.example.core.util.RxSchedulersUtil
import moxy.InjectViewState
import timber.log.Timber

@InjectViewState
class ShowToursPresenter(private val interactor: IToursInteractor) : BasePresenter<ShowToursView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadTours()
    }

    fun loadTours(fromRefresh: Boolean = false) {
        interactor.loadTours()
            .compose(RxSchedulersUtil.getIOToMainSingle())
            .doOnSubscribe { if (!fromRefresh) viewState.showProgress() }
            .doAfterTerminate { if (!fromRefresh) viewState.hideProgress() }
            .subscribe(
                {
                    viewState.showTours(it)
                },
                {
                    Timber.e(it)
                    // TODO: add errorHandler
                    viewState.showError()
                }
            )
            .untilDestroy()
    }

    fun loadToursFromRefresh() {
        loadTours(true)
    }
}