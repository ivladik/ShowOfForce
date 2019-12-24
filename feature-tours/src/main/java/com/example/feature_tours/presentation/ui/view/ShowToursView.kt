package com.example.feature_tours.presentation.ui.view

import com.example.feature_tours.domain.model.Tour
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ShowToursView : MvpView { // TODO: delete

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTours(tours: List<Tour>?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()

    @StateStrategyType(SkipStrategy::class)
    fun showProgress()

    @StateStrategyType(SkipStrategy::class)
    fun hideProgress()
}