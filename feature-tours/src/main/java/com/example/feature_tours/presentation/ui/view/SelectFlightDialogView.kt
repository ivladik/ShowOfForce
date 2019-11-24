package com.example.feature_tours.presentation.ui.view

import com.example.feature_tours.domain.model.AvailableEntireTourDomainModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SelectFlightDialogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showAvailableEntireTours(availableEntireTours: List<AvailableEntireTourDomainModel>)
}