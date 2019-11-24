package com.example.feature_tours.di.screen.select_flights

import com.example.feature_tours.di.screen.ToursScreenScope
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.presentation.presenter.SelectFlightDialogPresenter
import dagger.Module
import dagger.Provides

@Module
class SelectFlightModule {

    @Provides
    @ToursScreenScope
    fun provideSelectFlightDialogPresenter(
        toursInteractor: IToursInteractor
    ): SelectFlightDialogPresenter {
        return SelectFlightDialogPresenter(
            toursInteractor
        )
    }
}