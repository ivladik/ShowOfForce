package com.example.feature_tours.di.screen.show_tours

import com.example.feature_tours.di.screen.ToursScreenScope
import com.example.feature_tours.domain.interactor.IToursInteractor
import com.example.feature_tours.presentation.presenter.ShowToursPresenter
import dagger.Module
import dagger.Provides

@Module
class ShowToursModule {

    @Provides
    @ToursScreenScope
    fun provideToursPresenter(
        toursInteractor: IToursInteractor
    ): ShowToursPresenter {
        return ShowToursPresenter(
            toursInteractor
        )
    }
}